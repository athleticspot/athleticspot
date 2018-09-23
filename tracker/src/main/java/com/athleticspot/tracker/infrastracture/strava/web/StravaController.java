package com.athleticspot.tracker.infrastracture.strava.web;

import com.athleticspot.tracker.application.strava.StravaAuthService;
import com.athleticspot.tracker.application.strava.TrackerAuth;
import com.athleticspot.tracker.infrastracture.web.SportTrackersApiUrl;
import com.google.gson.Gson;
import javastrava.api.v3.rest.API;
import javastrava.api.v3.rest.AuthorisationAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Tomasz Kasprzycki
 */
@RestController
@RequestMapping(SportTrackersApiUrl.STRAVA_URL)
public class StravaController {

    private final Logger log = LoggerFactory.getLogger(StravaAuthService.class);

    private final TrackerAuth stravaTrackerAuth;

    AuthorisationAPI auth = API.authorisationInstance();

    public StravaController(TrackerAuth stravaTrackerAuth) {
        this.stravaTrackerAuth = stravaTrackerAuth;
    }

    //disable secuirty here
    @GetMapping(value = "/register/{username}")
    public void registerCode(@RequestParam String code,
                             @RequestParam String state,
                             @PathVariable String username) {
        log.debug("Registering username: {}", username);
        log.debug("code value: {}, state value: ", code, state);
        stravaTrackerAuth.fetchToken(code, username);
    }

    @GetMapping(value = "/activate")
    public String activateStravaUser(HttpServletRequest httpServletRequest) throws IOException {
        final Gson gson = new Gson();
        return gson.toJson(stravaTrackerAuth.authenticateTracker()) ;
    }

}
//Steps
//1. Use activateStravaUser method to get activation link
//2. Response from 1 executes registerCode method which fetch Strava token
//3. Strava token from 2 should be stored for future reference
