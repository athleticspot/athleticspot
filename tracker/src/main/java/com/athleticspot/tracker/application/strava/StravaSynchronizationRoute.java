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

    private final StravaSynchronizationService stravaSynchronizationService;

    @Autowired
    public StravaSynchronizationRoute(StravaApplicationService stravaApplicationService, StravaSynchronizationService stravaSynchronizationService) {
        this.stravaApplicationService = stravaApplicationService;
        this.stravaSynchronizationService = stravaSynchronizationService;
    }

    @Override
    public void configure() {
        from("quartz2://simpleTimer?cron=0/30+*+*+*+*+?&autoStartScheduler=true")
            .log("Strava synchronization start")
            .bean(stravaSynchronizationService, "retrieveUsers")
            .split(body())
            .bean(stravaSynchronizationService, "retrieveUserActivities")
            .to("mock:end");
    }
}
