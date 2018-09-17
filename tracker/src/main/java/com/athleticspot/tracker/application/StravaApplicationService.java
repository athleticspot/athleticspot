package com.athleticspot.tracker.application;

import com.athleticspot.tracker.domain.model.SportActivity;
import com.athleticspot.tracker.infrastracture.assambler.StravaActivityAssembler;
import com.google.common.collect.Lists;
import javastrava.api.v3.auth.model.Token;
import javastrava.api.v3.auth.model.TokenResponse;
import javastrava.api.v3.model.StravaActivity;
import javastrava.api.v3.rest.API;
import javastrava.api.v3.rest.ActivityAPI;
import javastrava.api.v3.rest.AuthorisationAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author Tomasz Kasprzycki
 */
@Component
public class StravaApplicationService {

    private final String clientSecret = "91ad80ea231505275883acc75d7c088c1cf07773";

    //This is application ID assign to athleticspot application
    private final int clientCode = 14842;

    //returned during user registration
    private final String code = "0ad0891d82ad2aab88ec82cb59829bb4ebda78c6";

    private final StravaActivityAssembler stravaActivityAssembler;

    @Autowired
    public StravaApplicationService(StravaActivityAssembler stravaActivityAssembler) {
        this.stravaActivityAssembler = stravaActivityAssembler;
    }


    public String clientSecret() {
        return clientSecret;
    }

    public int clientCode() {
        return clientCode;
    }

    public List<SportActivity> getStravaActivities(){
        AuthorisationAPI auth = API.authorisationInstance();

        TokenResponse response = auth.tokenExchange(
            this.clientCode(),
            this.clientSecret(),
            code);
        Token token = new Token(response);

        final ActivityAPI api = API.instance(ActivityAPI.class, token);
        final StravaActivity[] stravaActivities = api.listAuthenticatedAthleteActivities(
            1537041488,
            0,
            1,
            10
        );

        return stravaActivityAssembler.buildFromStravaActivities(Arrays.asList(stravaActivities));
    }
}
