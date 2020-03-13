package com.athleticspot.tracker.application.strava;

import com.athleticspot.common.SecurityUtils;
import com.athleticspot.tracker.application.TrackerAuth;
import com.athleticspot.tracker.application.TrackerUserService;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author Tomasz Kasprzycki
 */
@Service
public class StravaAuthService implements TrackerAuth {

    private final StravaApplicationServiceImpl stravaDataProvider;

    private final TrackerUserService trackerUserService;

    public StravaAuthService(StravaApplicationServiceImpl stravaDataProvider, TrackerUserService trackerUserService) {
        this.stravaDataProvider = stravaDataProvider;
        this.trackerUserService = trackerUserService;
    }

    @Override
    public String generateTrackerActivationUrl() {
        UriComponentsBuilder builder =
            UriComponentsBuilder.fromHttpUrl("https://www.strava.com/oauth/authorize")
                .queryParam("client_id", stravaDataProvider.clientCode())
                .queryParam("response_type", "code")//response should return code
                .queryParam("redirect_uri", "https://athleticspot.com/api/tracker/strava/register/" + SecurityUtils.getCurrentUserLogin())
                .queryParam("scope", "activity:read");
        return builder.build().encode().toUriString();
    }

    @Override
    public void storeStravaToken(String code, String username) {
        trackerUserService.addStravaCode(code, username);
    }
}
