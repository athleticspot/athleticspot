package com.athleticspot.tracker.domain.model;

/**
 * @author Tomasz Kasprzycki
 */
public class SportActivity {

    public String id;

    public String username;

    public String title;

    public TrackerSource trackerSource;

    public SportActivity(String username, String title, TrackerSource trackerSource) {
        this.username = username;
        this.title = title;
        this.trackerSource = trackerSource;
    }

    public static SportActivity create(String username, String title, TrackerSource trackerSource) {
        return new SportActivity(username, title, trackerSource);
    }
}
