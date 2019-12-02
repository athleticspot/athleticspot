package com.athleticspot.tracker.application.strava;

import javastrava.model.StravaActivity;

import java.time.LocalDateTime;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * @author Tomasz Kasprzycki
 */
public class StravaApplicationServiceXXX {

    private final StravaApi stravaApi;

    public StravaApplicationServiceXXX(StravaApi stravaApi) {
        this.stravaApi = stravaApi;
    }

    public List<StravaActivity> retrieveActivities(String username, LocalDateTime lastSynchronizationDate) {
        if ("username_with_activities".equals(username)) {
            int pageSize = 10;
            return new StravaSynchronizationService(stravaApi).retrieveNotSynchronizedSportActivities(pageSize, lastSynchronizationDate);
        } else {
            return newArrayList();
        }
    }
}
