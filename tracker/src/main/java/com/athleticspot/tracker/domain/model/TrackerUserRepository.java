package com.athleticspot.tracker.domain.model;

import java.util.List;
import java.util.Optional;

/**
 * @author Tomasz Kasprzycki
 */
public interface TrackerUserRepository {


    /**
     * Finds all users stored within system
     */
    List<TrackerUser> findAllUsers();

    /**
     * @param trackerUser
     */
    void saveTrackerUser(TrackerUser trackerUser);


    TrackerUser getUser(String userLogin);

    TrackerUser save(TrackerUser assignStravaLastSynchronizationDate);

    List<TrackerUser> getStravaUsers();

    Optional<TrackerUser> findByLogin(String login);
}
