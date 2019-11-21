package com.athleticspot.tracker.application.strava;

import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Tomasz Kasprzycki
 */
@Component
public class StravaSynchronizationRoute extends SpringRouteBuilder {

    private final StravaApplicationService stravaApplicationService;

    @Autowired
    public StravaSynchronizationRoute(StravaApplicationService stravaApplicationService) {
        this.stravaApplicationService = stravaApplicationService;
    }

    @Override
    public void configure() {
        from("quartz2://simpleTimer?cron=0/30+*+*+*+*+?&autoStartScheduler=true")
            .log("Strava synchronization start")
            .bean(stravaApplicationService, "retrieveStravaUsers")
            .split(body())
            .bean(stravaApplicationService, "synchronizedStravaActivities")
            .to("mock:end");
    }
}
