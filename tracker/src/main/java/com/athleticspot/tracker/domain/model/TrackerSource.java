package com.athleticspot.tracker.domain.model;

/**
 * @author Tomasz Kasprzycki
 */
public enum TrackerSource {

    MANUAL("Manual"),
    STRAVA("Strava");

    private String source;

    TrackerSource(String source) {
        this.source = source;
    }

    public String getSource() {
        return source;
    }
}
