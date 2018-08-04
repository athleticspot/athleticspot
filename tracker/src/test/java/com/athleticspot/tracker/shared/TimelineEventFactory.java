package com.athleticspot.tracker.shared;

import com.athleticspot.tracker.domain.model.SportActivityDetails;

import java.time.LocalDateTime;

/**
 * @author Tomasz Kasprzycki
 */
public class TimelineEventFactory {

    public static SportActivityDetails testSportActivity() {
        String sportActivityDescription = "description";
        String sportActivityTitle = "title";
        String sportActivityType = "running";
        String sportActivityDuration = "2h 25s";
        String sportActivityDistance = "10.5";
        String sportActivityUnits = "Metric";
        String sportActivityMaxSpeed = "12";
        String sportActivityMeanSpeed = "5";
        LocalDateTime sportActivityDateTime = LocalDateTime.now();

        SportActivityDetails sportActivityDetails = SportActivityDetails.create(
            sportActivityDescription,
            sportActivityTitle,
            sportActivityType,
            sportActivityDuration,
            sportActivityDistance,
            sportActivityUnits,
            sportActivityMaxSpeed,
            sportActivityMeanSpeed,
            sportActivityDateTime
        );
        return sportActivityDetails;
    }

}
