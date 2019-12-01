package com.athleticspot.tracker.application.strava;

import javastrava.model.StravaActivity;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

/**
 * @author Tomasz Kasprzycki
 */
public class StravaApplicationServiceTest {

    private static final String USERNAME_WITHOUT_ACTIVITIES = "username_without_activities";
    private static final String USERNAME_WITH_ACTIVITIES = "username_with_activities";
    private StravaApplicationService stravaApplicationService;

    @Before
    public void setUp() throws Exception {
        stravaApplicationService = new StravaApplicationServiceImpl(null, null, null, null);
    }

    @Test
    public void empty_list_result_for_user_without_strava_activities() {
        List<StravaActivity> stravaSportActivities = stravaApplicationService.retrieveActivities(USERNAME_WITHOUT_ACTIVITIES);
        Assertions.assertThat(stravaSportActivities).isEmpty();
    }

    @Test
    public void sport_activity_list_for_user_with_strava_activities() {
        List<StravaActivity> stravaSportActivities = stravaApplicationService.retrieveActivities(USERNAME_WITH_ACTIVITIES);
        Assertions.assertThat(stravaSportActivities).isNotEmpty();
    }

    @Test
    @Ignore
    public void sport_activity_list_contains_activities_after_last_synchronized_date() {
    }
}

//test for activities between range
/**
 * STRAVA api specification
 * before – Unix epoch time in seconds - return activities before this time
 *public StravaActivity[] listAuthenticatedAthleteActivities(@Query("before") final Integer before, @Query("after") final Integer after, @Query("page") final Integer page,
 *                        @Query("per_page") final Integer perPage) throws BadRequestException;
 *
 * 	/**
 *
 *
 */

//test for particular page size
//test for number of pages
