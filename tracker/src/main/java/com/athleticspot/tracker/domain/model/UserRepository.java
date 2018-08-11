package com.athleticspot.tracker.domain.model;

/**
 * @author Tomasz Kasprzycki
 */
public interface UserRepository {

    ApplicationUserId getCurrentUserId();

    String getTimelineIdentifier();

    /**
     * Assigns timeline identifier to particular user.
     *
     * @param user_id
     * @param availableTimelineId
     */
    void addTimelineIdentifier(String user_id, String availableTimelineId);
}
