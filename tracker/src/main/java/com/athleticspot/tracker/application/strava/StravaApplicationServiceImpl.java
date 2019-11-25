package com.athleticspot.tracker.application.strava;

import com.athleticspot.tracker.application.TrackerUserService;
import com.athleticspot.tracker.domain.graph.Athlete;
import com.athleticspot.tracker.domain.graph.GraphAthleteRepository;
import com.athleticspot.tracker.domain.graph.SportActivity;
import com.athleticspot.tracker.domain.model.SportActivityBuilder;
import com.athleticspot.tracker.domain.model.TrackerUser;
import com.athleticspot.tracker.infrastracture.security.SecurityService;
import javastrava.api.API;
import javastrava.api.ActivityAPI;
import javastrava.api.AuthorisationAPI;
import javastrava.auth.model.Token;
import javastrava.auth.model.TokenResponse;
import javastrava.model.StravaActivity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Tomasz Kasprzycki
 */
@Component
public class StravaApplicationServiceImpl implements StravaApplicationService {

    private final Logger log = LoggerFactory.getLogger(StravaApplicationServiceImpl.class);

    private final TrackerUserService trackerUserService;

    private final GraphAthleteRepository graphAthleteRepository;

    private final SecurityService securityService;

    private final String clientSecret = "91ad80ea231505275883acc75d7c088c1cf07773";

    //This is application ID assign to athleticspot application
    @Value(value = "${strava.client_id}")
    private int clientCode;

    //returned during user registration
    private final String code = "0ad0891d82ad2aab88ec82cb59829bb4ebda78c6";

    private final AuthorisationAPI auth = API.authorisationInstance();

    private final StravaApi stravaApi;


    @Autowired
    public StravaApplicationServiceImpl(TrackerUserService trackerUserService,
                                        GraphAthleteRepository graphAthleteRepository,
                                        SecurityService securityService,
                                        StravaApi stravaApi) {
        this.trackerUserService = trackerUserService;
        this.graphAthleteRepository = graphAthleteRepository;
        this.securityService = securityService;
        this.stravaApi = stravaApi;
    }

    public String clientSecret() {
        return clientSecret;
    }

    public int clientCode() {
        return clientCode;
    }

    private String getCode(String username) {
        return trackerUserService.getStravaCode(username);
    }

    @Override
    public List<TrackerUser> retrieveStravaUsers() {
        return trackerUserService.getStravaUsers();
    }

    @Override
    public void synchronizedStravaActivities(TrackerUser trackerUser) {
        final String username = trackerUser.getLogin();
        final ActivityAPI api = API.instance(ActivityAPI.class, getUserToken(username));
        final LocalDateTime now = LocalDateTime.now();
        long synchronizationAfterDate = getStravaLastSynchronizationDateAsEpoch(username);
        int pageNumber = 1;
        while (true) {
            StravaActivity[] stravaActivities = api.listAuthenticatedAthleteActivities(
                (int) now.atZone(ZoneId.systemDefault()).toEpochSecond(),
                (int) synchronizationAfterDate,
                pageNumber,
                10
            );
            log.info("Page number: {}", pageNumber);
            log.info("Page size: {}", stravaActivities.length);
            pageNumber++;
            if (stravaActivities.length == 0) {
                break;
            }
            Athlete athlete = getAthlete(username);
            final List<SportActivity> sportActivities = assembleStravaActivitiesFrom(stravaActivities, athlete);
            athlete.perform(sportActivities);
            graphAthleteRepository.save(athlete);
        }
        trackerUserService.assignStravaLastSynchronizationDate(now, username);
    }

    private Athlete getAthlete(String username) {
        return securityService.getAthleteByName(username)
            .orElseThrow(() -> new IllegalStateException(String.format("There is no athlete with name %s", username)));
    }

    private List<SportActivity> assembleStravaActivitiesFrom(StravaActivity[] stravaActivities, Athlete athlete) {
        return Arrays.stream(stravaActivities)
            .map(stravaActivity -> SportActivityBuilder.createFromStravaActivity(stravaActivity, athlete.getAthleteUUID(), athlete.getFirstAndLastName()).createSportActivity())
            .collect(Collectors.toList());
    }

    private long getStravaLastSynchronizationDateAsEpoch(String username) {
        final LocalDateTime stravaLastSynchronizationDate = trackerUserService.getStravaLastSynchronizationDate(username);
        if (Objects.isNull(stravaLastSynchronizationDate)) {
            return 0;
        }
        return stravaLastSynchronizationDate.atZone(ZoneId.systemDefault()).toEpochSecond();
    }

    private Token getUserToken(String username) {
        Assert.notNull(username, "Username cannot be null");
        TokenResponse response = auth.tokenExchange(
            this.clientCode(),
            this.clientSecret(),
            this.getCode(username));
        return new Token(response);
    }
}
