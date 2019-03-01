package com.athleticspot.tracker.domain.model;

/**
 * @author Tomasz Kasprzycki
 */
public enum TrackerSource {

    MANUAL(0),
    STRAVA(1);

    private Integer source;

    TrackerSource(Integer source) {
        this.source = source;
    }

    public Integer getSource() {
        return source;
    }
}
