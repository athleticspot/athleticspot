package com.athleticspot.tracker.application;

import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Tomasz Kasprzycki
 */
//@Component
public class StravaSynchronizationService extends SpringRouteBuilder {

    private final StravaApplicationService stravaApplicationService;

    @Autowired
    public StravaSynchronizationService(StravaApplicationService stravaApplicationService) {
        this.stravaApplicationService = stravaApplicationService;
    }

    @Override
    public void configure() {
        from("quartz2://simpleTimer?cron=0/30+*+*+*+*+?&autoStartScheduler=true")
            .log("Strava synchronization start")
            .bean(stravaApplicationService, "retrieveStravaUsers")
            .split(body()).parallelProcessing()
            .bean(stravaApplicationService, "synchronizedStravaActivities")
            .to("mock:end");
    }
}
