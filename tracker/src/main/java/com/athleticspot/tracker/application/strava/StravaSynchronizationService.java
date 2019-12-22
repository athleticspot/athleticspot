package com.athleticspot.tracker.application.strava;

import com.athleticspot.tracker.application.TrackerUserService;
import com.google.common.collect.Lists;
import javastrava.model.StravaActivity;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static java.util.Objects.isNull;

/**
 * @author Tomasz Kasprzycki
 */
public class StravaSynchronizationService {

    private final StravaApi stravaApi;
    private final TrackerUserService trackerUserService;

    public StravaSynchronizationService(StravaApi stravaApi, TrackerUserService trackerUserService) {
        this.stravaApi = stravaApi;
        this.trackerUserService = trackerUserService;
    }

    List<StravaActivity> retrieveNotSynchronizedSportActivities(int pageSize, long activitiesAfter) {
        int pageNumber = 0;
        final List<StravaActivity> result = Lists.newArrayList();
        while (true) {
            final List<StravaActivity> fetchedStravaActivities = stravaApi.getSportActivities(pageNumber, pageSize, activitiesAfter);
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
        return retrieveNotSynchronizedSportActivities(pageSize, stravaLastSynchronizationDateAsEpoch);
    }

    private long getStravaLastSynchronizationDateAsEpoch(String username) {
        final LocalDateTime stravaLastSynchronizationDate = trackerUserService.getStravaLastSynchronizationDate(username);
        if (isNull(stravaLastSynchronizationDate)) {
            return 0;
        }
        return stravaLastSynchronizationDate.toEpochSecond(ZoneOffset.UTC);
    }
}
