package com.athleticspot.tracker.domain.model;

import javastrava.api.v3.model.StravaActivity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Tomasz Kasprzycki
 */
@Document(collection = "strava_activities")
public class SportActivity {

    @Id
    public String id;

    public String username;

    public String title;

    public TrackerSource trackerSource;

    public static SportActivity create(String username, String title, TrackerSource trackerSource) {
        return new SportActivity(username, title, trackerSource);
    }

    public static SportActivity creteFromStravaActivity(final StravaActivity stravaActivity, String username) {
        return new SportActivity(
            username,
            stravaActivity.getName(),
            TrackerSource.STRAVA
        );
    }

    public SportActivity setId(final String id) {
        this.id = id;
        return this;
    }

    private SportActivity(String username, String title, TrackerSource trackerSource) {
        this.username = username;
        this.title = title;
        this.trackerSource = trackerSource;
    }
}
