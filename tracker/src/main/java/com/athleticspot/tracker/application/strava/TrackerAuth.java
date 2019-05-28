package com.athleticspot.tracker.application.strava;

/**
 * @author Tomasz Kasprzycki
 */
public interface TrackerAuth {

    String authenticateTracker();

    void storeStravaToken(String code, String username);
}
