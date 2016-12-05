package net.zeleon.core;

import org.atteo.classindex.IndexSubclasses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 05.12.2016.
 *
 * @author Steffen Jørgensen (mrsteffenjo@gmail.com)
 * @version 1
 */
@IndexSubclasses
public abstract class AbstractCollector implements Collector{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<Collector> getDependencies() {
        return new ArrayList<>();
    }

    @Override
    public Map<String, Object> resolveDependencies(RiffiContext context) {
        if(logger.isDebugEnabled()){
            logger.debug("Resolving dependencies for context: {}", context);
        }
        Map<String, Object> unifiedCollection = new HashMap<>();
        for (Collector collector : getDependencies()) {
            unifiedCollection.putAll(collector.collect(context));
        }
        return unifiedCollection;
    }
}
