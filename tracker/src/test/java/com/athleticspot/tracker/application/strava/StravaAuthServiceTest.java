package com.athleticspot.tracker.application.strava;

import com.athleticspot.tracker.application.StravaApplicationService;
import javastrava.api.v3.auth.TokenManager;
import javastrava.api.v3.auth.model.Token;
import javastrava.api.v3.auth.model.TokenResponse;
import javastrava.api.v3.model.StravaActivity;
import javastrava.api.v3.rest.API;
import javastrava.api.v3.rest.ActivityAPI;
import javastrava.api.v3.rest.AuthorisationAPI;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * @author Tomasz Kasprzycki
 */
public class StravaAuthServiceTest {

    //This is code which was returned by strava application
    private final String code = "0ad0891d82ad2aab88ec82cb59829bb4ebda78c6";

    @Test
    public void getTokenTest() {

        StravaApplicationService stravaApplicationService = new StravaApplicationService(null);

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

        StravaApplicationService stravaApplicationService = new StravaApplicationService(null);

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
            10
        );

        Assertions.assertThat(stravaActivities).isNotEmpty();
    }




}
