package com.athleticspot.tracker.domain.model;

import java.time.LocalDateTime;

/**
 * @author Tomasz Kasprzycki
 */
public class SportActivity {

    private String id;

    private String username;

    private String title;

    private TrackerSource trackerSource;

    private LocalDateTime startDate;

    private SportActivity(String id, String username, String title, TrackerSource trackerSource, LocalDateTime startDate) {
        this.username = username;
        this.title = title;
        this.trackerSource = trackerSource;
        this.id = id;
        this.startDate = startDate;
    }

    public static SportActivity create(String id,
                                       String username,
                                       String title,
                                       TrackerSource trackerSource,
                                       LocalDateTime startDate) {
        return new SportActivity(id, username, title, trackerSource, startDate);
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getTitle() {
        return title;
    }

    public TrackerSource getTrackerSource() {
        return trackerSource;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }
}
