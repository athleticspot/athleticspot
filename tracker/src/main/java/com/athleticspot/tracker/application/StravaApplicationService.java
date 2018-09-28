package com.athleticspot.tracker.application;

import com.athleticspot.common.SecurityUtils;
import com.athleticspot.tracker.domain.model.GeneralSportActivityRepository;
import com.athleticspot.tracker.domain.model.SportActivity;
import com.athleticspot.tracker.domain.model.StravaSportActivity;
import com.athleticspot.tracker.infrastracture.assambler.StravaActivityAssembler;
import javastrava.api.v3.auth.model.Token;
import javastrava.api.v3.auth.model.TokenResponse;
import javastrava.api.v3.model.StravaActivity;
import javastrava.api.v3.rest.API;
import javastrava.api.v3.rest.ActivityAPI;
import javastrava.api.v3.rest.AuthorisationAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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
public class StravaApplicationService {

    private final Logger log = LoggerFactory.getLogger(StravaApplicationService.class);

    private final TrackerUserService trackerUserService;

    private final StravaActivityAssembler stravaActivityAssembler;

    private final GeneralSportActivityRepository generalSportActivityRepository;

    private final String clientSecret = "91ad80ea231505275883acc75d7c088c1cf07773";

    //This is application ID assign to athleticspot application
    private final int clientCode = 14842;

    //returned during user registration
    private final String code = "0ad0891d82ad2aab88ec82cb59829bb4ebda78c6";

    private final AuthorisationAPI auth = API.authorisationInstance();


    @Autowired
    public StravaApplicationService(TrackerUserService trackerUserService, StravaActivityAssembler stravaActivityAssembler, GeneralSportActivityRepository generalSportActivityRepository) {
        this.trackerUserService = trackerUserService;
        this.stravaActivityAssembler = stravaActivityAssembler;
        this.generalSportActivityRepository = generalSportActivityRepository;
    }


    public String clientSecret() {
        return clientSecret;
    }

    public int clientCode() {
        return clientCode;
    }

    private String getCode() {
        return trackerUserService.getStravaCode(SecurityUtils.getCurrentUserLogin());
    }

    public List<SportActivity> getStravaActivities() {
        Token token = getToken();

        final ActivityAPI api = API.instance(ActivityAPI.class, token);
        final StravaActivity[] stravaActivities = api.listAuthenticatedAthleteActivities(
            (int) LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond(),
            0,
            1,
            200
        );

        return stravaActivityAssembler.buildFromStravaActivities(Arrays.asList(stravaActivities));
    }

    //TODO: This should be separate thread per user.
    @Scheduled(cron = "*/15 * * * * *")
    public void synchronizedStravaActivities() {
        final ActivityAPI api = API.instance(ActivityAPI.class, getToken());
        final LocalDateTime now = LocalDateTime.now();
        long synchronizationAfterDate = getStravaLastSynchronizationDateAsEpoch("admin");
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

            final List<StravaSportActivity> ts = Arrays.stream(stravaActivities)
                .map(stravaActivity -> new StravaSportActivity().setProperties(stravaActivity, "admin"))
                .collect(Collectors.toList());
            generalSportActivityRepository.save(ts);
        }
        trackerUserService.assignStravaLastSynchronizationDate(now, "admin");
    }

    private long getStravaLastSynchronizationDateAsEpoch(String username) {
        final LocalDateTime stravaLastSynchronizationDate = trackerUserService.getStravaLastSynchronizationDate(username);
        if (Objects.isNull(stravaLastSynchronizationDate)) {
            return 0;
        }
        return stravaLastSynchronizationDate.atZone(ZoneId.systemDefault()).toEpochSecond();
    }

    private Token getToken() {
        //TODO : we need to get List of tokens for all users.
        TokenResponse response = auth.tokenExchange(
            this.clientCode(),
            this.clientSecret(),
            this.getCode());
        return new Token(response);
    }
}