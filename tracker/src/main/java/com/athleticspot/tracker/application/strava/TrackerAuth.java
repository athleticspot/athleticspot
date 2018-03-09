package com.athleticspot.tracker.application.strava;

/**
 * @author Tomasz Kasprzycki
 */
public interface TrackerAuth {

    String activateTracker();

    void authenticateTracker(String secret, String code);
}
