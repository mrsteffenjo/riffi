package net.zeleon.collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import net.zeleon.config.JsonTestCollectorConfiguration;
import net.zeleon.core.AbstractCollector;
import net.zeleon.core.RiffiContext;
import net.zeleon.core.annotations.Collector;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 05.12.2016.
 *
 * @author Steffen Jørgensen (mrsteffenjo@gmail.com)
 * @version 1
 */
@Collector
public class JsonTestCollector extends AbstractCollector {
    public static final String SERVICE_IDENTIFIER = "myService/module1/test";

    private final JsonTestCollectorConfiguration configuration;

    public JsonTestCollector(JsonTestCollectorConfiguration configuration1) {
        this.configuration = configuration1;
    }

    @Override
    public String getIdentifier() {
        return SERVICE_IDENTIFIER;
    }

    @Override
    public Map<String, Object> collect(RiffiContext context) {
        Map<String, Object> result = new HashMap<>();
        try {
            Map<String, Object> data = new HashMap<>();
            InputStream jsonInputStream = ClassLoader.getSystemResourceAsStream(configuration.getResourceLocation());
            List<Map<String, Object>> mapping = configuration.getObjectMapper().readValue(jsonInputStream, new TypeReference<List<Map<String, Object>>>() {
            });
            for (Map<String, Object> map : mapping) {
                data.put(map.get("id").toString(), map);
            }
            result.put(SERVICE_IDENTIFIER, data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
