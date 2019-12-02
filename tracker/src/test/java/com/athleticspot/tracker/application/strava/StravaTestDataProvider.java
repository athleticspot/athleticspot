package com.athleticspot.tracker.application.strava;

import javastrava.model.StravaActivity;
import javastrava.model.reference.StravaActivityType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import static org.assertj.core.util.Lists.newArrayList;

/**
 * @author Tomasz Kasprzycki
 */
public class StravaTestDataProvider {

    static StravaActivity createStravaActivity(float distance, Long stravaId, LocalDateTime startDate) {
        StravaActivity result = new StravaActivity();
        result.setId(stravaId);
        result.setType(StravaActivityType.BACKCOUNTRY_SKI);
        result.setDistance(distance);
        result.setStartDateLocal(startDate);
        return result;
    }

    public static List<StravaActivity> createMockStravaActivities(int activityPageSize) {
        List<StravaActivity> result = newArrayList();
        for (int i = 0; i < activityPageSize; i++) {
            final StravaActivity stravaActivity = new StravaActivity();
            stravaActivity.setId(new Random().nextLong());
            stravaActivity.setDistance(new Random().nextFloat());
            result.add(stravaActivity);
        }
        return result;
    }
}
