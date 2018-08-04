package com.athleticspot.tracker.application.strava;

import com.athleticspot.tracker.application.StravaDataFactory;
import javastrava.api.v3.auth.TokenManager;
import javastrava.api.v3.auth.model.Token;
import javastrava.api.v3.auth.model.TokenResponse;
import javastrava.api.v3.rest.API;
import javastrava.api.v3.rest.AuthorisationAPI;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * @author Tomasz Kasprzycki
 */
public class StravaAuthServiceTest {

    private final String clientSecret = "91ad80ea231505275883acc75d7c088c1cf07773";

    public final String code = "0ad0891d82ad2aab88ec82cb59829bb4ebda78c6";

    @Test
    public void getTokenTest() {

//
//        StravaAuthService test = new StravaAuthService(stravaDataProvider, stravaUser);
//
//        test.fetchToken();
        StravaDataFactory stravaDataFactory = new StravaDataFactory();

        AuthorisationAPI auth = API.authorisationInstance();

        TokenResponse response = auth.tokenExchange(
            stravaDataFactory.clientCode(),
            stravaDataFactory.clientSecret(),
            code);
        Token token = new Token(response);
        TokenManager.instance().storeToken(token);


        Assertions.assertThat(true).isTrue();
    }

}
