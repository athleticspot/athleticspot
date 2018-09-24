package com.athleticspot.tracker.application;

/**
 * @author Tomasz Kasprzycki
 */
public interface TrackerUserService {

    String getTimelineIdentifier();

    void addTimelineIdentifier(String availableTimelineId);

    void addStravaCode(String stravaCode, String username);

    String getStravaCode(String username);
}
