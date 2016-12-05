package net.zeleon.core;

import net.zeleon.core.annotations.CollectorConfiguration;
import org.atteo.classindex.ClassIndex;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.PicoContainer;
import org.picocontainer.injectors.ConstructorInjection;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 03.12.2016.
 *
 * @author Steffen Jørgensen (mrsteffenjo@gmail.com)
 * @version 1
 */
public class RiffiContext {
    private static RiffiContext instance;
    private final PicoContainer container;
    private final Map<String, Collector> collectors;

    private RiffiContext() {
        final DefaultPicoContainer defaultPicoContainer = new DefaultPicoContainer(new ConstructorInjection());

        // Adding Collector configuration
        for (Class<?> clz : ClassIndex.getAnnotated(CollectorConfiguration.class)) {
            defaultPicoContainer.addComponent(clz);
        }

        // Adding Collectors
        this.collectors = new HashMap<>();
        for (Class<?> clz : ClassIndex.getAnnotated(net.zeleon.core.annotations.Collector.class)) {
            if(Collector.class.isAssignableFrom(clz)){
                defaultPicoContainer.addComponent(clz);
                Collector collector = (Collector) defaultPicoContainer.getComponent(clz);
                collectors.put(collector.getIdentifier(), collector);
            }else{
                throw new RuntimeException(String.format("The class %s is not assignable from interface %s", clz.getCanonicalName(), Collector.class.getCanonicalName()));
            }
        }

        this.container = defaultPicoContainer;
    }

    public static RiffiContext getInstance(){
        if(instance == null){
            instance = new RiffiContext();
        }
        return instance;
    }

    public <T> T getBean(Class<T> type){
        return container.getComponent(type);
    }

    public Collector getCollector(String identifier){
       return this.collectors.get(identifier);
    }
}
