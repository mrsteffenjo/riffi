package net.zeleon.core;

import org.atteo.classindex.IndexSubclasses;

import java.util.List;
import java.util.Map;

/**
 * Created on 05.12.2016.
 *
 * @author Steffen Jørgensen (mrsteffenjo@gmail.com)
 * @version 1
 */
public interface Collector {
    String getIdentifier();
    Map<String, Object> collect(RiffiContext context);
    List<Collector> getDependencies();
    Map<String, Object> resolveDependencies(RiffiContext context);
}
