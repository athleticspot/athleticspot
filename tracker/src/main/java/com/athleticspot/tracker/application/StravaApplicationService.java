package com.athleticspot.tracker.application;

import com.athleticspot.tracker.domain.model.TrackerUser;

import java.util.List;

/**
 * @author Tomasz Kasprzycki
 */
public interface StravaApplicationService {

    List<TrackerUser> retrieveStravaUsers();

    void synchronizedStravaActivities(TrackerUser trackerUser);
}
