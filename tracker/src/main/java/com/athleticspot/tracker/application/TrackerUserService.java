package com.athleticspot.tracker.application;

import com.athleticspot.tracker.domain.model.TrackerUser;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Tomasz Kasprzycki
 */
public interface TrackerUserService {

    void addTimelineIdentifier(String availableTimelineId);

    void addStravaCode(String stravaCode, String username);

    String getStravaCode(String username);

    void assignStravaLastSynchronizationDate(LocalDateTime synchronizationBeforeDate, String admin);

    LocalDateTime getStravaLastSynchronizationDate(String username);

    List<TrackerUser> getStravaUsers();
}
