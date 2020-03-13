package com.athleticspot.tracker.application.strava;

import com.athleticspot.tracker.application.TrackerUserService;
import com.athleticspot.tracker.domain.model.TrackerUser;
import com.google.common.collect.Lists;
import javastrava.model.StravaActivity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

/**
 * @author Tomasz Kasprzycki
 */
@Service
public class StravaSynchronizationService {

    private final Logger LOG = LoggerFactory.getLogger(StravaSynchronizationService.class);

    private final StravaApi stravaApi;
    private final TrackerUserService trackerUserService;

    @Autowired
    public StravaSynchronizationService(StravaApi stravaApi, TrackerUserService trackerUserService) {
        this.stravaApi = stravaApi;
        this.trackerUserService = trackerUserService;
    }

    public List<String> retrieveUsers() {
        return trackerUserService.getStravaUsers().stream()
            .map(TrackerUser::getLogin)
            .collect(Collectors.toList());
    }

    public List<StravaActivity> retrieveUserActivities(String username) {
        LOG.info("fetching strava activities for: {}", username);
        int pageSize = 10;
        final long stravaLastSynchronizationDateAsEpoch = getStravaLastSynchronizationDateAsEpoch(username);
        final List<StravaActivity> result = retrieveNotSynchronizedSportActivities(username, pageSize, stravaLastSynchronizationDateAsEpoch);
        LOG.info("Strava synchronization result: {}", result);
        return result;
    }

    List<StravaActivity> retrieveNotSynchronizedSportActivities(String username, int pageSize, long activitiesAfter) {
        int pageNumber = 1;
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

    private long getStravaLastSynchronizationDateAsEpoch(String username) {
        final LocalDateTime stravaLastSynchronizationDate = trackerUserService.getStravaLastSynchronizationDate(username);
        if (isNull(stravaLastSynchronizationDate)) {
            return 0;
        }
        return stravaLastSynchronizationDate.toEpochSecond(ZoneOffset.UTC);
    }
}
