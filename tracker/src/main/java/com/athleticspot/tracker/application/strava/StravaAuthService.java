package com.athleticspot.tracker.application.strava;

import javastrava.api.v3.auth.TokenManager;
import javastrava.api.v3.auth.model.Token;
import javastrava.api.v3.auth.model.TokenResponse;
import javastrava.api.v3.rest.API;
import javastrava.api.v3.rest.AuthorisationAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author Tomasz Kasprzycki
 */
public class StravaAuthService implements TrackerAuth {

    private final Logger log = LoggerFactory.getLogger(StravaAuthService.class);

    private final int stravaClientCode = 14842;

    @Override
    public String activateTracker() {

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://www.strava.com/oauth/authorize")
            .queryParam("client_id", "14842")
            .queryParam("response_type", "code")
            .queryParam("redirect_uri", "https://athleticspot.com/api/strava/exchange")
            .queryParam("scope", "write");
        return builder.build().encode().toUriString();

    }

    @Override
    public void authenticateTracker(String secret, String code) {

        log.info("Exchanging strava token");
        log.info("Strava secret value: {} code value: {}", secret, code);
        AuthorisationAPI auth = API.authorisationInstance();

        TokenResponse response = auth.tokenExchange(
            stravaClientCode,
            secret,
            code);
        Token token = new Token(response);
        TokenManager.instance().storeToken(token);

        TokenManager.instance().retrieveTokenWithExactScope("tomkasp@gmail.com");
    }
}
