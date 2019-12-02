package com.athleticspot.tracker.application.strava;

import javastrava.model.StravaActivity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static com.athleticspot.tracker.application.strava.StravaTestDataProvider.createStravaActivity;
import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * @author Tomasz Kasprzycki
 */
@RunWith(MockitoJUnitRunner.class)
public class StravaSynchronizationServiceTest {

    private static final int FIRST_PAGE = 0;
    private static final int SECOND_PAGE = 1;
    private static final LocalDateTime ACTIVITIES_AFTER = LocalDateTime.of(LocalDate.of(2019, 11, 20), LocalTime.of(20, 10));

    @Mock
    private StravaApi stravaApi;

    private StravaSynchronizationService stravaApplicationService;

    @Before
    public void init() {
        stravaApplicationService = new StravaSynchronizationService(stravaApi);
    }

    @Test
    public void zero_activities_returns_when_there_are_no_activities_in_strava() {
        //given:
        final int pageSize = 10;
        when(stravaApi.getSportActivities(FIRST_PAGE, pageSize, ACTIVITIES_AFTER)).thenReturn(newArrayList());

        //when:
        List<StravaActivity> stravaActivities = stravaApplicationService.retrieveNotSynchronizedSportActivities(
            pageSize,
            ACTIVITIES_AFTER
        );

        //then:
        assertThat(stravaActivities).isEmpty();
        verify(stravaApi, times(1)).getSportActivities(FIRST_PAGE, pageSize, ACTIVITIES_AFTER);
        verifyNoMoreInteractions(stravaApi);
    }

    @Test
    public void fetch_all_activities_when_there_are_less_activities_than_page_size() {
        //given:
        final int pageSize = 10;
        when(stravaApi.getSportActivities(FIRST_PAGE, pageSize, ACTIVITIES_AFTER)).thenReturn(newArrayList(
            createStravaActivity(10f, 1L, LocalDateTime.now()),
            createStravaActivity(200f, 2L, LocalDateTime.now()),
            createStravaActivity(1000f, 3L, LocalDateTime.now())
            )
        );

        //when:
        List<StravaActivity> stravaActivities = stravaApplicationService.retrieveNotSynchronizedSportActivities(
            pageSize,
            ACTIVITIES_AFTER
        );

        //then
        assertThat(stravaActivities).hasSize(3);
    }


    @Test
    public void fetch_two_pages_when_there_are_more_sport_activities_than_one_page_size() {
        //given
        int pageSize = 3;
        when(stravaApi.getSportActivities(FIRST_PAGE, pageSize, ACTIVITIES_AFTER)).thenReturn(
            newArrayList(
                createStravaActivity(10f, 1L, LocalDateTime.now()),
                createStravaActivity(200f, 2L, LocalDateTime.now()),
                createStravaActivity(1000f, 3L, LocalDateTime.now())
            )
        );
        when(stravaApi.getSportActivities(SECOND_PAGE, pageSize, ACTIVITIES_AFTER)).thenReturn(
            newArrayList(
                createStravaActivity(50f, 4L, LocalDateTime.now()),
                createStravaActivity(200.20f, 5L, LocalDateTime.now())
            )
        );

        //when:
        List<StravaActivity> stravaSportActivities = stravaApplicationService.retrieveNotSynchronizedSportActivities(pageSize, ACTIVITIES_AFTER);

        //then:
        assertThat(stravaSportActivities).hasSize(5);
    }

    @Test
    public void request_one_page_only_in_case_of_result_from_Strava_less_than_page_size() {
        //given:
        final int pageSize = 10;
        when(stravaApi.getSportActivities(FIRST_PAGE, pageSize, ACTIVITIES_AFTER)).thenReturn(newArrayList(
            createStravaActivity(10f, 1L, LocalDateTime.now()),
            createStravaActivity(200f, 2L, LocalDateTime.now()),
            createStravaActivity(1000f, 3L, LocalDateTime.now())
            )
        );

        //when:
        List<StravaActivity> stravaActivities = stravaApplicationService.retrieveNotSynchronizedSportActivities(
            pageSize,
            ACTIVITIES_AFTER
        );

        //then
        assertThat(stravaActivities).hasSize(3);
        verify(stravaApi, times(1)).getSportActivities(FIRST_PAGE, pageSize, ACTIVITIES_AFTER);
        verifyNoMoreInteractions(stravaApi);
    }
}
