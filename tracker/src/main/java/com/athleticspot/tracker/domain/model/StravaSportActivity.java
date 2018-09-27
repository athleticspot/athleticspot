package com.athleticspot.tracker.domain.model;

import javastrava.api.v3.model.StravaActivity;
import org.springframework.data.annotation.Id;

/**
 * @author Tomasz Kasprzycki
 */
public class StravaSportActivity extends StravaActivity {

        @Id
        public String id;

        public TrackerSource trackerSource = TrackerSource.STRAVA;

}
