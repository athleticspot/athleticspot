package com.athleticspot.tracker.application.strava;

import javastrava.api.v3.auth.TokenManager;
import javastrava.api.v3.auth.model.Token;
import javastrava.api.v3.model.StravaActivity;
import javastrava.api.v3.rest.API;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author Tomasz Kasprzycki
 */
@Component
public class StravaActionsImpl implements TrackerActions {

    private final Logger LOG = LoggerFactory.getLogger(StravaActionsImpl.class);

    @Override
    public void fetchActivities() {

        final Token token = TokenManager.instance().retrieveTokenWithExactScope("tomkasp@gmail.com");


//        Optional<Token> optionalAPI = Optional.ofNullable();

            API api = new API(token);
            StravaActivity[] stravaActivities = api.listAuthenticatedAthleteActivities(null, null, 1, 10);

            Arrays.asList(stravaActivities).forEach(activity ->
                LOG.info(String.valueOf(activity))
            );
//            return Arrays.stream(stravaActivities).map(stravaActivity -> new StravaActivitiesDto()
//                .distance(stravaActivity.getDistance())
//                .maxSpeed(stravaActivity.getMaxSpeed())
//                .name(stravaActivity.getName())
//                .type(stravaActivity.getType().toString())).collect(Collectors.toList());
//            //TODO handle situation where there is no token
    }
}
