package com.athleticspot.tracker.infrastracture.strava.web;

import com.athleticspot.tracker.application.TrackerAuth;
import com.athleticspot.tracker.application.strava.StravaAuthService;
import com.athleticspot.tracker.infrastracture.web.SportTrackersApiUrl;
import com.google.gson.Gson;
import javastrava.api.API;
import javastrava.api.AuthorisationAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Tomasz Kasprzycki
 */
//Steps
//1. Use activateStravaUser method to get activation link
//2. Response from 1 executes registerCode method which fetch Strava token
//3. Strava token from 2 should be stored for future reference
@RestController
@RequestMapping(SportTrackersApiUrl.STRAVA_URL)
public class StravaController {

    private final Logger log = LoggerFactory.getLogger(StravaAuthService.class);

    private final TrackerAuth stravaTrackerAuth;

    AuthorisationAPI auth = API.authorisationInstance();

    @Value("${application.url}")
    String stravaRedirectUrl;

    public StravaController(TrackerAuth stravaTrackerAuth) {
        this.stravaTrackerAuth = stravaTrackerAuth;
    }

    @GetMapping(value = "/register/{username}")
    public void registerCode(@RequestParam String code,
                                     @RequestParam String state,
                                     @PathVariable String username,
                                     HttpServletResponse httpServletResponse) throws IOException {
        log.debug("Registering username for strava: {}", username);
        log.debug("code value: {}, state value: {}", code, state);
        stravaTrackerAuth.storeStravaToken(code, username);
        httpServletResponse.sendRedirect(stravaRedirectUrl + "/#/strava");

    }

    @GetMapping(value = "/activate")
    public String activateStravaUser(HttpServletRequest httpServletRequest) throws IOException {
        final Gson gson = new Gson();
        return gson.toJson(stravaTrackerAuth.authenticateTracker());
    }

}
