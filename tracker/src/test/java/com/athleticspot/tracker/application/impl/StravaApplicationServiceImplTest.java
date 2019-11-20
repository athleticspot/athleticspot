package com.athleticspot.tracker.application.impl;

import com.athleticspot.tracker.application.TrackerUserService;
import com.athleticspot.tracker.domain.graph.GraphAthleteRepository;
import com.athleticspot.tracker.infrastracture.security.SecurityService;
import javastrava.model.StravaActivity;
import javastrava.model.reference.StravaActivityType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * @author Tomasz Kasprzycki
 */
@RunWith(MockitoJUnitRunner.class)
public class StravaApplicationServiceImplTest {

    private static final int FIRST_PAGE = 0;
    private static final int SECOND_PAGE = 1;
    private static final LocalDateTime ACTIVITIES_AFTER = LocalDateTime.of(LocalDate.of(2019, 11, 20), LocalTime.of(20, 10));

    @Mock
    private TrackerUserService trackerUserService;

    @Mock
    private GraphAthleteRepository graphAthleteRepository;

    @Mock
    private SecurityService securityService;

    @Mock
    private StravaApi stravaApi;

    private StravaApplicationServiceImpl stravaApplicationService;

    @Before
    public void init() {
        stravaApplicationService = new StravaApplicationServiceImpl(
            trackerUserService,
            graphAthleteRepository,
            securityService,
            stravaApi);
    }

    @Test
    public void fetch_all_activities_when_there_are_less_activities_than_page_size() {
        //given:
        final int pageSize = 10;
        when(stravaApi.getSportActivities(FIRST_PAGE, pageSize, ACTIVITIES_AFTER)).thenReturn(newArrayList(
            createStravaActivity(10f, 1L),
            createStravaActivity(200f, 2L),
            createStravaActivity(1000f, 3L)
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
                createStravaActivity(10f, 1L),
                createStravaActivity(200f, 2L),
                createStravaActivity(1000f, 3L)
            )
        );
        when(stravaApi.getSportActivities(SECOND_PAGE, pageSize, ACTIVITIES_AFTER)).thenReturn(
            newArrayList(
                createStravaActivity(50f, 4L),
                createStravaActivity(200.20f, 5L)
            )
        );

        //when:
        List<StravaActivity> stravaSportActivities = stravaApplicationService.retrieveNotSynchronizedSportActivities(pageSize, ACTIVITIES_AFTER);

        //then:
        assertThat(stravaSportActivities).hasSize(5);
    }

//


    private StravaActivity createStravaActivity(float distance, Long stravaId) {
        StravaActivity result = new StravaActivity();
        result.setId(stravaId);
        result.setType(StravaActivityType.BACKCOUNTRY_SKI);
        result.setDistance(distance);
        return result;
    }

}
