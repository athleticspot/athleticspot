package com.athleticspot.tracker.application.strava;

import com.athleticspot.tracker.domain.model.TrackerUser;
import javastrava.model.StravaActivity;

import java.util.List;

/**
 * @author Tomasz Kasprzycki
 */
public interface StravaApplicationService {

    List<TrackerUser> retrieveStravaUsers();

    void synchronizedStravaActivities(TrackerUser trackerUser);

    List<StravaActivity> retrieveActivities(String username);
}
