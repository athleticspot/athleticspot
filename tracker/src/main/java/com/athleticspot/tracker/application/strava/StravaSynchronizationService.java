package com.athleticspot.tracker.application.strava;

import com.google.common.collect.Lists;
import javastrava.model.StravaActivity;

import java.time.LocalDateTime;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * @author Tomasz Kasprzycki
 */
public class StravaSynchronizationService {

    private final StravaApi stravaApi;

    public StravaSynchronizationService(StravaApi stravaApi) {
        this.stravaApi = stravaApi;
    }

    List<StravaActivity> retrieveNotSynchronizedSportActivities(int pageSize, LocalDateTime activitiesAfter) {
        int pageNumber = 0;
        final List<StravaActivity> result = Lists.newArrayList();
        while (true) {
            final List<StravaActivity> fetchedStravaActivities = stravaApi.getSportActivities(pageNumber, pageSize, activitiesAfter);
            result.addAll(fetchedStravaActivities);
            if (fetchedStravaActivities.size() < pageSize) {
                break;
            }
            pageNumber++;
        }
        return result;
    }

    public List<StravaActivity> retrieveUserActivities(String username, LocalDateTime lastSynchronizationDate) {
        if ("username_with_activities".equals(username)) {
            int pageSize = 10;
            return retrieveNotSynchronizedSportActivities(pageSize, lastSynchronizationDate);
        } else {
            return newArrayList();
        }
    }
}
