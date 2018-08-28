package com.athleticspot.tracker.domain.model;

import java.util.List;

/**
 * @author Tomasz Kasprzycki
 */
public interface UserRepository {

    ApplicationUserId getCurrentUserId();

    /**
     * Gets timeline identifier which is assigned to the user.
     * To fetch this we use user login.
     * @param userLogin
     * @return
     */
    String getTimelineIdentifier(String userLogin);

    /**
     * Assigns timeline identifier to particular user.
     * User fetch is done based on user login.
     *
     * @param userLogin
     * @param timelineIdentifier
     */
    void addTimelineIdentifier(String userLogin, String timelineIdentifier);

    /**
     * Finds all users stored within system
     */
    List<TrackerUser> findAllUsers();
}
