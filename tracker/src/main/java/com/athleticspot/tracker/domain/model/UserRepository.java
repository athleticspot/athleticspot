package com.athleticspot.tracker.domain.model;

import java.util.List;

/**
 * @author Tomasz Kasprzycki
 */
public interface UserRepository {

    ApplicationUserId getCurrentUserId();

    String getTimelineIdentifier(String currentUserLogin);

    /**
     * Assigns timeline identifier to particular user.
     *
     * @param user_id
     * @param availableTimelineId
     */
    void addTimelineIdentifier(String user_id, String availableTimelineId);

    /**
     * Finds all users stored within system
     */
    List<TrackerUser> findAllUsers();
}
