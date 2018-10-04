package com.athleticspot.tracker.shared;

import com.athleticspot.tracker.domain.model.SportActivityType;
import com.athleticspot.tracker.domain.model.manual.ManualSportActivityDetails;

import java.time.LocalDateTime;

/**
 * @author Tomasz Kasprzycki
 */
public class TimelineEventFactory {

    public static ManualSportActivityDetails testSportActivity() {
        String sportActivityDescription = "description";
        String sportActivityTitle = "title";
        SportActivityType sportActivityType = SportActivityType.RUN;
        String sportActivityDuration = "2h 25s";
        String sportActivityDistance = "10.5";
        String sportActivityUnits = "Metric";
        String sportActivityMaxSpeed = "12";
        String sportActivityMeanSpeed = "5";
        LocalDateTime sportActivityDateTime = LocalDateTime.now();

        ManualSportActivityDetails manualSportActivityDetails = ManualSportActivityDetails.create(
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
        return manualSportActivityDetails;
    }

}
