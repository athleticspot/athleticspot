package com.athleticspot.tracker.domain.model.strava;

import com.athleticspot.tracker.domain.model.SportActivityGenericType;
import com.athleticspot.tracker.domain.model.TrackerSource;
import javastrava.api.v3.model.StravaActivity;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
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

    private LocalDateTime startDate;

    public static StravaSportActivity creteFromStravaActivity(final StravaActivity stravaActivity, String username) {
        return new StravaSportActivity(
            username,
            stravaActivity.getName(),
            TrackerSource.STRAVA,
            stravaActivity.getStartDateLocal()
        );
    }

    public StravaSportActivity setId(final String id) {
        this.id = id;
        return this;
    }

    private StravaSportActivity(String username, String title, TrackerSource trackerSource, LocalDateTime startDate) {
        this.username = username;
        this.title = title;
        this.trackerSource = trackerSource;
        this.sportyActivityIdentifier = UUID.randomUUID().toString();
        this.startDate = startDate;
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

    public String getSportyActivityIdentifier() {
        return sportyActivityIdentifier;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }
}
