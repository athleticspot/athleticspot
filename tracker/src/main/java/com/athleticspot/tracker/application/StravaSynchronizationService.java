package com.athleticspot.tracker.application;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * @author Tomasz Kasprzycki
 */
@Component
public class StravaSynchronizationService extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("quartz2://simpleTimer?cron=0/10+*+*+*+*+*")
            .log("Reading msgs")
            .to("mock:end");
    }
}
