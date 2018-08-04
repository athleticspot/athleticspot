package com.athleticspot.tracker.domain.model;

/**
 * @author Tomasz Kasprzycki
 */
public interface UserRepository {

    ApplicationUserId getCurrentUserId();

    String getTimelineIdentifier();
}
