package com.athleticspot.tracker.application.strava;

import com.athleticspot.common.SecurityUtils;
import com.athleticspot.tracker.application.TrackerUserService;
import com.athleticspot.tracker.application.impl.StravaApplicationServiceImpl;
import javastrava.api.API;
import javastrava.api.AuthorisationAPI;
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

    private final StravaApplicationServiceImpl stravaDataProvider;

    private final TrackerUserService trackerUserService;

    public StravaAuthService(StravaApplicationServiceImpl stravaDataProvider, TrackerUserService trackerUserService) {
        this.stravaDataProvider = stravaDataProvider;
        this.trackerUserService = trackerUserService;
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
                .queryParam("scope", "read");
        return builder.build().encode().toUriString();
    }

    /**
     * Authenticate user. Fetch token required for communication with strava.
     *
     * @param code     code from strava application
     * @param username athleticspot username
     */
    @Override
    public void storeStravaToken(String code, String username) {
        log.info("Exchanging strava token");
        log.info("Strava code value: {}", code);
        AuthorisationAPI auth = API.authorisationInstance();

        trackerUserService.addStravaCode(code, username);
    }
}
