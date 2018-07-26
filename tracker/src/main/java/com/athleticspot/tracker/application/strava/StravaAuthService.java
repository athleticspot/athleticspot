package com.athleticspot.tracker.application.strava;

import com.athleticspot.common.SecurityUtils;
import com.athleticspot.tracker.application.StravaDataFactory;
import com.athleticspot.tracker.domain.TrackerUser;
import javastrava.api.v3.auth.TokenManager;
import javastrava.api.v3.auth.model.Token;
import javastrava.api.v3.auth.model.TokenResponse;
import javastrava.api.v3.rest.API;
import javastrava.api.v3.rest.AuthorisationAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author Tomasz Kasprzycki
 */
@Service
public class StravaAuthService implements TrackerAuth {

    private final Logger log = LoggerFactory.getLogger(StravaAuthService.class);

    private final StravaDataFactory stravaDataProvider;

    private final TrackerUser stravaUser;

    public StravaAuthService(StravaDataFactory stravaDataProvider, TrackerUser stravaUser) {
        this.stravaDataProvider = stravaDataProvider;
        this.stravaUser = stravaUser;
    }

    /**
     * Generates strava activation link. We specify redirect url which is handle separately.
     *
     * @return String which is uri
     */
    @Override
    public String authenticateTracker() {
        UriComponentsBuilder builder =
            UriComponentsBuilder.fromHttpUrl("https://www.strava.com/oauth/authorize")
                .queryParam("client_id", stravaDataProvider.clientCode())
                .queryParam("response_type", "code")//response should return code
                .queryParam("redirect_uri", "https://athleticspot.com/api/tracker/strava/register/" + SecurityUtils.getCurrentUserLogin())
                .queryParam("scope", "write");
        return builder.build().encode().toUriString();
    }

    /**
     * Authenticate user. Fetch token required for communication with strava.
     *
     * @param code     code from strava application
     * @param username athleticspot username
     */
    @Override
    public void fetchToken(String code, String username) {
        final String clientSecret = stravaDataProvider.clientSecret();
        final int clientCode = stravaDataProvider.clientCode();

        log.info("Exchanging strava token");
        log.info("Strava code value: {}", code);
        AuthorisationAPI auth = API.authorisationInstance();

        TokenResponse response = auth.tokenExchange(clientCode, clientSecret, code);
        Token token = new Token(response);
        TokenManager.instance().storeToken(token);

        //TDOD: store token with strava username and athleticspot username in athleticspot database
        TokenManager.instance().retrieveTokenWithExactScope("tomkasp@gmail.com");
    }
}
