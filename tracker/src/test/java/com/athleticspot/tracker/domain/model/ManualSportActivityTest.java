package com.athleticspot.tracker.domain.model;

import com.athleticspot.tracker.domain.model.manual.ManualSportActivity;
import com.athleticspot.tracker.domain.model.manual.ManualSportActivityDetails;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Tomasz Kasprzycki
 */
public class ManualSportActivityTest {

    @Test
    public void whenNewSportActivityIsCreteadItHasAllRequiredProperties() {
        //given
        String sportActivityIdentifier = UUID.randomUUID().toString();
        String sportActivitySource = "Manual";

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
            sportActivityMeanSpeed
        );

        //when
        ManualSportActivity manualSportActivity = ManualSportActivity
            .create(
                sportActivityIdentifier,
                sportActivitySource,
                manualSportActivityDetails,
                sportActivityDateTime
            );

        //then
        Assertions.assertThat(manualSportActivity.identifier()).isEqualTo(sportActivityIdentifier);
        Assertions.assertThat(manualSportActivity.source()).isEqualTo(sportActivitySource);
        Assertions.assertThat(manualSportActivity.getStartDate()).isEqualTo(sportActivityDateTime);
        ManualSportActivityDetails sportActivityAssignedDetails = manualSportActivity.details();
        Assertions.assertThat(sportActivityAssignedDetails.description()).isEqualTo(sportActivityDescription);
        Assertions.assertThat(sportActivityAssignedDetails.distance()).isEqualTo(sportActivityDistance);
        Assertions.assertThat(sportActivityAssignedDetails.duration()).isEqualTo(sportActivityDuration);
        Assertions.assertThat(sportActivityAssignedDetails.maxSpeed()).isEqualTo(sportActivityMaxSpeed);
        Assertions.assertThat(sportActivityAssignedDetails.meanSpeed()).isEqualTo(sportActivityMeanSpeed);
        Assertions.assertThat(sportActivityAssignedDetails.title()).isEqualTo(sportActivityTitle);
        Assertions.assertThat(sportActivityAssignedDetails.type()).isEqualTo(sportActivityType);
        Assertions.assertThat(sportActivityAssignedDetails.units()).isEqualTo(sportActivityUnits);

    }
}
