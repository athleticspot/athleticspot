package com.athleticspot.tracker.application.strava;

import com.athleticspot.tracker.application.TrackerUserService;
import javastrava.model.StravaActivity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Random;

import static com.athleticspot.tracker.application.strava.StravaTestDataProvider.createMockStravaActivities;
import static com.athleticspot.tracker.application.strava.StravaTestDataProvider.createStravaActivity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Lists.newArrayList;
import static org.mockito.Mockito.when;

/**
 * @author Tomasz Kasprzycki
 */
@RunWith(MockitoJUnitRunner.class)
public class StravaIntegrationUserTest {

    private static final int FIRST_PAGE = 0;
    private static final int SECOND_PAGE = 1;
    private static final int ACTIVITY_PAGE_SIZE = 10;
    private static final String USERNAME_WITHOUT_ACTIVITIES = "username_without_activities";
    private static final String USERNAME_WITH_ACTIVITIES = "username_with_activities";
    private Long lastSynchronizationDate;
    private LocalDateTime synchronizationDate;

    @Mock
    private StravaApi stravaApi;

    @Mock
    private TrackerUserService trackerUserService;

    private StravaSynchronizationService stravaSynchronizationService;


    @Before
    public void setUp() throws Exception {
        synchronizationDate = LocalDateTime.of(2019, 02, 27, 11, 20);
        lastSynchronizationDate = synchronizationDate.toEpochSecond(ZoneOffset.UTC);
        stravaSynchronizationService = new StravaSynchronizationService(stravaApi, trackerUserService);
    }

    @Test
    public void empty_list_result_for_user_without_strava_activities() {
        List<StravaActivity> stravaSportActivities = stravaSynchronizationService.retrieveUserActivities(USERNAME_WITHOUT_ACTIVITIES);
        assertThat(stravaSportActivities).isEmpty();
    }

    @Test
    public void sport_activity_list_for_user_with_strava_activities() {
        when(trackerUserService.getStravaLastSynchronizationDate(USERNAME_WITH_ACTIVITIES)).thenReturn(synchronizationDate);
        when(stravaApi.getSportActivities(USERNAME_WITH_ACTIVITIES, FIRST_PAGE, ACTIVITY_PAGE_SIZE, lastSynchronizationDate)).thenReturn(newArrayList(
            createStravaActivity(new Random().nextFloat(), new Random().nextLong(), LocalDateTime.ofEpochSecond(lastSynchronizationDate + 1, 0, ZoneOffset.UTC))
        ));
        List<StravaActivity> stravaSportActivities = stravaSynchronizationService.retrieveUserActivities(USERNAME_WITH_ACTIVITIES);
        assertThat(stravaSportActivities).isNotEmpty();
    }

    @Test
    public void sport_activity_list_contains_activities_after_last_synchronized_date() {
        when(trackerUserService.getStravaLastSynchronizationDate(USERNAME_WITH_ACTIVITIES)).thenReturn(synchronizationDate);
        when(stravaApi.getSportActivities(USERNAME_WITH_ACTIVITIES, FIRST_PAGE, ACTIVITY_PAGE_SIZE, lastSynchronizationDate)).thenReturn(newArrayList(
            createStravaActivity(new Random().nextFloat(), new Random().nextLong(), LocalDateTime.ofEpochSecond(lastSynchronizationDate + 1, 0, ZoneOffset.UTC))
        ));
        List<StravaActivity> stravaSportActivities = stravaSynchronizationService.retrieveUserActivities(USERNAME_WITH_ACTIVITIES);
        assertThat(stravaSportActivities).isNotEmpty();
        assertThat(stravaSportActivities).extracting(StravaActivity::getStartDateLocal)
            .allMatch(localDateTime -> lastSynchronizationDate < localDateTime.toEpochSecond(ZoneOffset.UTC));
    }

    @Test
    public void sport_activity_list_contains_two_pages_of_data() {
        when(trackerUserService.getStravaLastSynchronizationDate(USERNAME_WITH_ACTIVITIES)).thenReturn(synchronizationDate);
        when(stravaApi.getSportActivities(USERNAME_WITH_ACTIVITIES, FIRST_PAGE, ACTIVITY_PAGE_SIZE, lastSynchronizationDate)).thenReturn(createMockStravaActivities(ACTIVITY_PAGE_SIZE));
        when(stravaApi.getSportActivities(USERNAME_WITH_ACTIVITIES, SECOND_PAGE, ACTIVITY_PAGE_SIZE, lastSynchronizationDate)).thenReturn(createMockStravaActivities(ACTIVITY_PAGE_SIZE));
        List<StravaActivity> stravaSportActivities = stravaSynchronizationService.retrieveUserActivities(USERNAME_WITH_ACTIVITIES);
        assertThat(stravaSportActivities).hasSize(ACTIVITY_PAGE_SIZE * 2);
    }
}

//TODO: provide user and last synchronization date for user.
//TODO: OutofMemory error when strava returns more than page size
//DONE test for number of pages
//DONE test for activities between range
