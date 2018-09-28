package com.athleticspot.tracker.application;

import java.time.LocalDateTime;

/**
 * @author Tomasz Kasprzycki
 */
public interface TrackerUserService {

    String getTimelineIdentifier();

    void addTimelineIdentifier(String availableTimelineId);

    void addStravaCode(String stravaCode, String username);

    String getStravaCode(String username);

    void assignStravaLastSynchronizationDate(LocalDateTime synchronizationBeforeDate, String admin);

    LocalDateTime getStravaLastSynchronizationDate(String username);
}
