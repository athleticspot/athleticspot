package com.athleticspot.tracker.application.strava;

import com.athleticspot.tracker.application.TrackerUserService;
import com.athleticspot.tracker.application.impl.StravaApplicationServiceImpl;
import javastrava.api.v3.auth.TokenManager;
import javastrava.api.v3.auth.model.Token;
import javastrava.api.v3.auth.model.TokenResponse;
import javastrava.api.v3.model.StravaActivity;
import javastrava.api.v3.rest.API;
import javastrava.api.v3.rest.ActivityAPI;
import javastrava.api.v3.rest.AuthorisationAPI;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This test should be used only from local machine to test strava connection
 *
 * @author Tomasz Kasprzycki
 */
@Ignore()
public class StravaAuthServiceTest {

    private final Logger log = LoggerFactory.getLogger(StravaAuthServiceTest.class);

    //This is code which was returned by strava application
    private final String code = "0ad0891d82ad2aab88ec82cb59829bb4ebda78c6";

    @Mock
    TrackerUserService trackerUserService;

    @Before
    public void setUp() {
        trackerUserService = Mockito.mock(TrackerUserService.class);
        Mockito.when(trackerUserService.getStravaCode(Matchers.any())).thenReturn("0ad0891d82ad2aab88ec82cb59829bb4ebda78c6");

    }

    @Test
    public void getTokenTest() {

        StravaApplicationServiceImpl stravaApplicationService = new StravaApplicationServiceImpl(null, null, null, null);

        AuthorisationAPI auth = API.authorisationInstance();

        TokenResponse response = auth.tokenExchange(
            stravaApplicationService.clientCode(),
            stravaApplicationService.clientSecret(),
            code);
        Token token = new Token(response);
        TokenManager.instance().storeToken(token);


        Assertions.assertThat(true).isTrue();
    }

    @Test
    public void getActivitiesTest() {

        StravaApplicationServiceImpl stravaApplicationService = new StravaApplicationServiceImpl(null, null, null, null);

        AuthorisationAPI auth = API.authorisationInstance();

        TokenResponse response = auth.tokenExchange(
            stravaApplicationService.clientCode(),
            stravaApplicationService.clientSecret(),
            code);
        Token token = new Token(response);

        final ActivityAPI api = API.instance(ActivityAPI.class, token);
        final StravaActivity[] stravaActivities = api.listAuthenticatedAthleteActivities(
            1537041488,
            0,
            1,
            50
        );

        Assertions.assertThat(stravaActivities).isNotEmpty();
    }

    @Test
    public void pagingActivitiesTest() {
        StravaApplicationServiceImpl stravaApplicationService = new StravaApplicationServiceImpl(
            trackerUserService,
            null,
            null,
            null);

        stravaApplicationService.synchronizedStravaActivities(null);
    }


}
