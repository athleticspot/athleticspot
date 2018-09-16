package com.athleticspot.tracker.application.strava;

import com.athleticspot.tracker.domain.model.SportActivity;
import com.athleticspot.tracker.infrastracture.assambler.StravaActivityAssembler;
import javastrava.api.v3.model.StravaActivity;
import javastrava.api.v3.model.reference.StravaActivityType;
import javastrava.api.v3.model.reference.StravaWorkoutType;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.time.ZonedDateTime;

/**
 * @author Tomasz Kasprzycki
 */
public class StravaActivityTest {

        final String description = "description";
        final int id = 1;
        final String activityName = "activity name";
        final float averageSpeed = 21.5F;
        final float distance = 10f;
        final String startDateTime = "2007-12-03T10:15:30+01:00[Europe/Paris]";
        final int elapsedTime = 5000;
        final float maxSpeed = 33F;

    @Test
    public void whenStravaActivityIsConvertedThenSourceIsStrava(){
        StravaActivityAssembler sportActivityAssembler = new StravaActivityAssembler();

        SportActivity sportActivity = sportActivityAssembler.buildFromStrava(buildStravaActivity());

        Assertions.assertThat(sportActivity.source()).isEqualTo("STRAVA");
        Assertions.assertThat(sportActivity.details().type()).isEqualTo("RUN");
        Assertions.assertThat(sportActivity.details().description()).isEqualTo(description);
        Assertions.assertThat(sportActivity.details().title()).isEqualTo(activityName);
        Assertions.assertThat(sportActivity.details().dateTime()).isEqualTo(ZonedDateTime.parse(startDateTime));

    }

    private StravaActivity buildStravaActivity(){

        StravaActivity stravaActivity = new StravaActivity();

        stravaActivity.setId(id);
        stravaActivity.setName(activityName);
        stravaActivity.setDescription(description);
        stravaActivity.setAverageSpeed(averageSpeed);
        stravaActivity.setDistance(distance);
        stravaActivity.setStartDate(ZonedDateTime.parse(startDateTime));
        stravaActivity.setType(StravaActivityType.RUN);
        stravaActivity.setWorkoutType(StravaWorkoutType.LONG_RUN);
        stravaActivity.setElapsedTime(elapsedTime);
        stravaActivity.setMaxSpeed(maxSpeed);

        return stravaActivity;
    }
}
