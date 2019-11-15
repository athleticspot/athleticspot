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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Tomasz Kasprzycki
 */
@RunWith(MockitoJUnitRunner.class)
public class StravaApplicationServiceImplTest {

    private static final Long STRAVA_ID = 12311110L;

    @Mock
    private TrackerUserService trackerUserService;

    @Mock
    private GraphAthleteRepository graphAthleteRepository;

    @Mock
    private SecurityService securityService;

    private StravaApplicationServiceImpl stravaApplicationServiceImplTest;

    @Before
    public void init() {
        stravaApplicationServiceImplTest = new StravaApplicationServiceImpl(
            trackerUserService,
            graphAthleteRepository,
            securityService
        );
    }

    @Test
    public void sport_activity_fetching_from_strava() {
        //when:
        List<StravaActivity> stravaActivities = stravaApplicationServiceImplTest.retrieveNotSynchronizedSportActivities();

        //then:
        assertThat(stravaActivities).containsExactly(createStravaActivity());
    }

    //when user does new activity on strava then it's reflected in Athleticspot
//    @Test
//    public void sport_activity_is_synchronized_after_it_was_added_on_strava(){
//        StravaApplicationServiceImpl stravaApplicationServiceImplTest = new StravaApplicationServiceImpl(
//            trackerUserService,
//            graphAthleteRepository,
//            securityService
//        );
//
//        //when
//        List<StravaActivity> stravaActivities = stravaApplicationServiceImplTest.retrieveNotSynchronizedSportActivities();
//
//        //then:
//        Assertions.assertThat(stravaActivities).contains(createStravaActivity());
//    }

    private StravaActivity createStravaActivity() {
        StravaActivity result = new StravaActivity();
        result.setId(STRAVA_ID);
        result.setType(StravaActivityType.BACKCOUNTRY_SKI);
        result.setDistance(10F);
        return result;
    }

}
