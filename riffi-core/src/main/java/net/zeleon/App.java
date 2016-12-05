package net.zeleon;

import net.zeleon.core.Collector;
import net.zeleon.core.RiffiContext;
import net.zeleon.collectors.JsonTestCollector;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        Collector collector = RiffiContext.getInstance()
                .getCollector(JsonTestCollector.SERVICE_IDENTIFIER);

        System.out.println(collector.collect(RiffiContext.getInstance()));

    }
}
