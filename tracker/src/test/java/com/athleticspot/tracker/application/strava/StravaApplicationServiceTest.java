package com.athleticspot.tracker.application.strava;

import javastrava.model.StravaActivity;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.List;

/**
 * @author Tomasz Kasprzycki
 */
public class StravaApplicationServiceTest {

    private static final String USERNAME = "username";

    @Test
    public void empty_list_result_for_user_without_strava_activities() {
        StravaApplicationService stravaApplicationService = new StravaApplicationServiceImpl(null, null, null, null);
        List<StravaActivity> stravaSportActivities = stravaApplicationService.retrieveActivities(USERNAME);
        Assertions.assertThat(stravaSportActivities).isEmpty();
    }
}
