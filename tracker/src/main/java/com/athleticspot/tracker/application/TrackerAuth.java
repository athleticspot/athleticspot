package com.athleticspot.tracker.application;

/**
 * @author Tomasz Kasprzycki
 */
public interface TrackerAuth {

    String generateTrackerActivationUrl();

    void storeStravaToken(String code, String username);
}
