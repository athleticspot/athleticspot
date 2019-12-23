package com.athleticspot.tracker.application.strava;

import com.athleticspot.tracker.application.TrackerUserService;
import com.google.common.collect.Lists;
import javastrava.api.API;
import javastrava.api.ActivityAPI;
import javastrava.api.AuthorisationAPI;
import javastrava.auth.model.Token;
import javastrava.auth.model.TokenResponse;
import javastrava.model.StravaActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static org.springframework.util.Assert.notNull;

/**
 * @author Tomasz Kasprzycki
 */
@Component
public class StravaApi {

    private final TrackerUserService trackerUserService;
    private final AuthorisationAPI auth = API.authorisationInstance();

    @Autowired
    public StravaApi(TrackerUserService trackerUserService) {
        this.trackerUserService = trackerUserService;
    }

    public List<StravaActivity> getSportActivities(String username, Integer pageNumber, Integer pageSize, long activitiesAfter) {
        final ActivityAPI api = API.instance(ActivityAPI.class, getUserToken(username));
        StravaActivity[] stravaActivities = api.listAuthenticatedAthleteActivities(
            ((int) LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)),
            ((int) activitiesAfter),
            pageNumber,
            10
        );
        return Lists.newArrayList(stravaActivities);
    }

    private Token getUserToken(String username) {
        final String clientSecret = "91ad80ea231505275883acc75d7c088c1cf07773";
        final int clientCode = 14842;
        notNull(username, "Username cannot be null");
        TokenResponse response = auth.tokenExchange(
            clientCode,
            clientSecret,
            trackerUserService.getStravaCode(username));
        return new Token(response);
    }
}
