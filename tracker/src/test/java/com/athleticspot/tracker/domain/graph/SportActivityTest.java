package com.athleticspot.tracker.domain.graph;

import com.athleticspot.tracker.domain.model.SportActivityBuilder;
import javastrava.model.StravaActivity;
import javastrava.model.reference.StravaActivityType;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.UUID;

/**
 * @author Tomasz Kasprzycki
 */
public class SportActivityTest {


    private static final CharSequence STRAVA_ACTIVITY_URL = "https://www.strava.com/activities/2734103741";
    private static final Long STRAVA_ID = 2734103741L;

    @Test
    public void when_url_is_fetched_then_strava_url_is_generated(){
        //given
        SportActivity sportActivity = createSportActivity();

        //when
        final String urlUnderTest = sportActivity.activityUrl();

        //then
        Assertions.assertThat(urlUnderTest).isEqualToIgnoringCase(STRAVA_ACTIVITY_URL);
    }

    private SportActivity createSportActivity() {
        return SportActivityBuilder.createFromStravaActivity(
            createStravaActivity(),
            UUID.randomUUID().toString(),
            "Tom Kasp"
        ).createSportActivity();
    }

    private StravaActivity createStravaActivity() {
        StravaActivity result = new StravaActivity();
        result.setId(STRAVA_ID);
        result.setType(StravaActivityType.BACKCOUNTRY_SKI);
        result.setDistance(10F);
        return result;
    }

}
