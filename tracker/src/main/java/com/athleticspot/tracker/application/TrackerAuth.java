package com.athleticspot.tracker.application;

/**
 * @author Tomasz Kasprzycki
 */
public interface TrackerAuth {

    String authenticateTracker();

    void storeStravaToken(String code, String username);
}
