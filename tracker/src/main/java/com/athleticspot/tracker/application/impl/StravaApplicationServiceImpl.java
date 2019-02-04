package com.athleticspot.tracker.application.impl;

import com.athleticspot.common.SecurityUtils;
import com.athleticspot.tracker.application.StravaApplicationService;
import com.athleticspot.tracker.application.TrackerUserService;
import com.athleticspot.tracker.domain.graph.*;
import com.athleticspot.tracker.domain.model.TrackerUser;
import com.athleticspot.tracker.domain.model.manual.ManualSportActivity;
import com.athleticspot.tracker.infrastracture.assembler.StravaActivityAssembler;
import com.athleticspot.tracker.infrastracture.security.SecurityService;
import javastrava.api.v3.auth.model.Token;
import javastrava.api.v3.auth.model.TokenResponse;
import javastrava.api.v3.model.StravaActivity;
import javastrava.api.v3.rest.API;
import javastrava.api.v3.rest.ActivityAPI;
import javastrava.api.v3.rest.AuthorisationAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final StravaActivityAssembler stravaActivityAssembler;

    private final GraphAthleteRepository graphAthleteRepository;

    private final SecurityService securityService;

    private final String clientSecret = "91ad80ea231505275883acc75d7c088c1cf07773";

    //This is application ID assign to athleticspot application
    private final int clientCode = 14842;

    //returned during user registration
    private final String code = "0ad0891d82ad2aab88ec82cb59829bb4ebda78c6";

    private final AuthorisationAPI auth = API.authorisationInstance();

    @Autowired
    public StravaApplicationServiceImpl(TrackerUserService trackerUserService,
                                        StravaActivityAssembler stravaActivityAssembler,
                                        GraphAthleteRepository graphAthleteRepository,
                                        SecurityService securityService) {
        this.trackerUserService = trackerUserService;
        this.stravaActivityAssembler = stravaActivityAssembler;
        this.graphAthleteRepository = graphAthleteRepository;
        this.securityService = securityService;
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

    public List<ManualSportActivity> getStravaActivities() {
        Token token = getUserToken(SecurityUtils.getCurrentUserLogin());

        final ActivityAPI api = API.instance(ActivityAPI.class, token);
        final StravaActivity[] stravaActivities = api.listAuthenticatedAthleteActivities(
            (int) LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond(),
            0,
            1,
            200
        );
        return stravaActivityAssembler.buildFromStravaActivities(Arrays.asList(stravaActivities));
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

            Athlete athlete =
                securityService.getAthleteByName(username)
                    .orElseThrow(() -> new IllegalStateException(String.format("There is no athlete with name %s", username)));
            final List<SportActivity> sportActivities = Arrays.stream(stravaActivities)
                .map(stravaActivity -> SportActivityBuilder.createFromStravaActivity(stravaActivity, athlete.getAthleteUUID(), athlete.getFirstAndLastName()).createSportActivity())
                .collect(Collectors.toList());

            athlete.perform(sportActivities);
            graphAthleteRepository.save(athlete);

        }
        trackerUserService.assignStravaLastSynchronizationDate(now, username);
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
