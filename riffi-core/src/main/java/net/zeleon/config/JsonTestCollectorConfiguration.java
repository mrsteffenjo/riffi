package net.zeleon.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.zeleon.core.annotations.CollectorConfiguration;

/**
 * Created on 05.12.2016.
 *
 * @author Steffen Jørgensen (mrsteffenjo@gmail.com)
 * @version 1
 */
@CollectorConfiguration
public class JsonTestCollectorConfiguration {
    private String resourceLocation;
    private ObjectMapper objectMapper;

    public JsonTestCollectorConfiguration() {
        //pretend we get this for the system properties
        this.resourceLocation = "test.json";
        this.objectMapper = new ObjectMapper();
    }

    public String getResourceLocation() {
        return resourceLocation;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
