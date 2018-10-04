package com.athleticspot.tracker.application.strava;

import com.athleticspot.tracker.domain.model.SportActivityType;
import com.athleticspot.tracker.domain.model.manual.ManualSportActivity;
import com.athleticspot.tracker.infrastracture.assembler.StravaActivityAssembler;
import javastrava.api.v3.model.StravaActivity;
import javastrava.api.v3.model.reference.StravaActivityType;
import javastrava.api.v3.model.reference.StravaWorkoutType;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.time.LocalDateTime;

/**
 * @author Tomasz Kasprzycki
 */
public class StravaActivityTest {

    private final String description = "description";
    private final int id = 1;
    private final String activityName = "activity name";
    private final float averageSpeed = 21.5F;
    private final float distance = 10f;
    private final String startDateTime = "2007-12-03T10:15:30";
    private final int elapsedTime = 5000;
    private final float maxSpeed = 33F;

    @Test
    public void whenStravaActivityIsConvertedThenSourceIsStrava() {
        StravaActivityAssembler sportActivityAssembler = new StravaActivityAssembler();

        ManualSportActivity manualSportActivity = sportActivityAssembler.buildFromStrava(buildStravaActivity());

        Assertions.assertThat(manualSportActivity.source()).isEqualTo("STRAVA");
        Assertions.assertThat(manualSportActivity.details().type()).isEqualByComparingTo(SportActivityType.RUN);
        Assertions.assertThat(manualSportActivity.details().description()).isEqualTo(description);
        Assertions.assertThat(manualSportActivity.details().title()).isEqualTo(activityName);
        Assertions.assertThat(manualSportActivity.details().dateTime()).isEqualTo(LocalDateTime.parse(startDateTime));

    }

    private StravaActivity buildStravaActivity() {

        StravaActivity stravaActivity = new StravaActivity();

        stravaActivity.setId(id);
        stravaActivity.setName(activityName);
        stravaActivity.setDescription(description);
        stravaActivity.setAverageSpeed(averageSpeed);
        stravaActivity.setDistance(distance);
//        stravaActivity.setStartDate(ZonedDateTime.parse(startDateTime));
        stravaActivity.setType(StravaActivityType.RUN);
        stravaActivity.setWorkoutType(StravaWorkoutType.LONG_RUN);
        stravaActivity.setElapsedTime(elapsedTime);
        stravaActivity.setMaxSpeed(maxSpeed);
        stravaActivity.setStartDateLocal(LocalDateTime.parse(startDateTime));

        return stravaActivity;
    }
}
