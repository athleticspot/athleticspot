package com.athleticspot.tracker.infrastracture.assambler;

import com.athleticspot.tracker.domain.model.SportActivity;
import com.athleticspot.tracker.domain.model.SportActivityDetails;
import javastrava.api.v3.model.StravaActivity;

/**
 * @author Tomasz Kasprzycki
 */
public class StravaActivityAssembler {

    public SportActivity buildFromStrava(StravaActivity stravaActivity) {

        final SportActivityDetails sportActivityDetails = SportActivityDetails.create(
            stravaActivity.getDescription(),
            stravaActivity.getName(),
            stravaActivity.getWorkoutType().toString(),
            stravaActivity.getElapsedTime().toString(),
            stravaActivity.getDistance().toString(),
            null,
            stravaActivity.getMaxSpeed().toString(),
            stravaActivity.getAverageSpeed().toString(),
            null
        );

        return SportActivity.createNew(
            "STRAVA",
            sportActivityDetails
        );
    }
}
