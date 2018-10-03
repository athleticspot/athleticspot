package com.athleticspot.tracker.infrastracture.assambler;

import com.athleticspot.tracker.domain.model.ManualSportActivity;
import com.athleticspot.tracker.domain.model.SportActivityDetails;
import com.athleticspot.tracker.domain.model.SportActivityType;
import javastrava.api.v3.model.StravaActivity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Tomasz Kasprzycki
 */
@Component
public class StravaActivityAssembler {

    public ManualSportActivity buildFromStrava(StravaActivity stravaActivity) {

        final SportActivityDetails sportActivityDetails = SportActivityDetails.create(
            stravaActivity.getDescription(),
            stravaActivity.getName(),
            SportActivityType.valueOf(stravaActivity.getType().getValue().toUpperCase()),
            stravaActivity.getElapsedTime().toString(),
            stravaActivity.getDistance().toString(),
            null,
            stravaActivity.getMaxSpeed().toString(),
            stravaActivity.getAverageSpeed().toString(),
            stravaActivity.getStartDateLocal()
        );

        return ManualSportActivity.createNew(
            "STRAVA",
            sportActivityDetails
        );
    }

    public List<ManualSportActivity> buildFromStravaActivities(List<StravaActivity> stravaActivities) {
        return stravaActivities.stream()
            .map(this::buildFromStrava)
            .collect(Collectors.toList());
    }
}
