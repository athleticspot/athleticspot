package com.athleticspot.tracker.application.strava;

import com.athleticspot.tracker.application.TrackerUserService;
import com.google.common.collect.Lists;
import javastrava.api.API;
import javastrava.api.AuthorisationAPI;
import javastrava.auth.model.Token;
import javastrava.auth.model.TokenResponse;
import javastrava.model.StravaActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static java.util.Objects.isNull;
import static org.springframework.util.Assert.notNull;

/**
 * @author Tomasz Kasprzycki
 */
@Service
public class StravaSynchronizationService {

    private final StravaApi stravaApi;
    private final TrackerUserService trackerUserService;
    private final AuthorisationAPI auth = API.authorisationInstance();

    @Autowired
    public StravaSynchronizationService(StravaApi stravaApi, TrackerUserService trackerUserService) {
        this.stravaApi = stravaApi;
        this.trackerUserService = trackerUserService;
    }

    List<StravaActivity> retrieveNotSynchronizedSportActivities(String username, int pageSize, long activitiesAfter) {
        int pageNumber = 0;
        final List<StravaActivity> result = Lists.newArrayList();
        while (true) {
            final List<StravaActivity> fetchedStravaActivities = stravaApi.getSportActivities(username, pageNumber, pageSize, activitiesAfter);
            result.addAll(fetchedStravaActivities);
            if (fetchedStravaActivities.size() < pageSize) {
                break;
            }
            pageNumber++;
        }
        return result;
    }

    public List<StravaActivity> retrieveUserActivities(String username) {
        int pageSize = 10;
        final long stravaLastSynchronizationDateAsEpoch = getStravaLastSynchronizationDateAsEpoch(username);
        return retrieveNotSynchronizedSportActivities(username, pageSize, stravaLastSynchronizationDateAsEpoch);
    }

    private long getStravaLastSynchronizationDateAsEpoch(String username) {
        final LocalDateTime stravaLastSynchronizationDate = trackerUserService.getStravaLastSynchronizationDate(username);
        if (isNull(stravaLastSynchronizationDate)) {
            return 0;
        }
        return stravaLastSynchronizationDate.toEpochSecond(ZoneOffset.UTC);
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
