package com.athleticspot.tracker.application.strava;

/**
 * @author Tomasz Kasprzycki
 */
public interface TrackerAuth {

    String authenticateTracker();

    void fetchToken(String code, String username);
}
