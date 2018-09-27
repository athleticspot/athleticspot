package com.athleticspot.tracker.domain.model;

import javastrava.api.v3.model.StravaActivity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Tomasz Kasprzycki
 */
@Document(collection = "strava_activities")
public class StravaSportActivity {

    @Id
    public String id;

    public String title;

    public TrackerSource trackerSource = TrackerSource.STRAVA;

    public StravaSportActivity setId(final String id) {
        this.id = id;
        return this;
    }

    public StravaSportActivity setTrackerSource(final TrackerSource trackerSource) {
        this.trackerSource = trackerSource;
        return this;
    }

    public StravaSportActivity setProperties(final StravaActivity id) {
        this.title = id.getName();
        return this;
    }


}
