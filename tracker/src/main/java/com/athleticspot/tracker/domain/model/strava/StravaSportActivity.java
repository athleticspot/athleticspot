package com.athleticspot.tracker.domain.model.strava;

import com.athleticspot.tracker.domain.model.SportActivityGenericType;
import com.athleticspot.tracker.domain.model.TrackerSource;
import javastrava.api.v3.model.StravaActivity;
import org.springframework.data.annotation.Id;

import java.util.UUID;

/**
 * @author Tomasz Kasprzycki
 */
public class StravaSportActivity extends SportActivityGenericType {

    @Id
    private String id;

    private String sportyActivityIdentifier;

    private String title;

    private TrackerSource trackerSource;

    public static StravaSportActivity create(String username, String title, TrackerSource trackerSource) {
        return new StravaSportActivity(username, title, trackerSource);
    }

    public static StravaSportActivity creteFromStravaActivity(final StravaActivity stravaActivity, String username) {
        return new StravaSportActivity(
            username,
            stravaActivity.getName(),
            TrackerSource.STRAVA
        );
    }

    public StravaSportActivity setId(final String id) {
        this.id = id;
        return this;
    }

    private StravaSportActivity(String username, String title, TrackerSource trackerSource) {
        this.username = username;
        this.title = title;
        this.trackerSource = trackerSource;
        this.sportyActivityIdentifier = UUID.randomUUID().toString();
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
}
