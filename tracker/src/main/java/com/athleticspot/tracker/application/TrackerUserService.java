package com.athleticspot.tracker.application;

import com.athleticspot.tracker.domain.model.ApplicationUserId;

/**
 * @author Tomasz Kasprzycki
 */
public interface TrackerUserService {

    String getTimelineIdentifier();

    void addTimelineIdentifier(String user_id, String availableTimelineId);

    ApplicationUserId getCurrentUserId();
}
