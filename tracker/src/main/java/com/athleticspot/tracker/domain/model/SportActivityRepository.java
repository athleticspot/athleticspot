package com.athleticspot.tracker.domain.model;

/**
 * @author Tomasz Kasprzycki
 */
public interface SportActivityRepository {

    void store(SportActivity sportActivity);

    String nextSportActivityId();

    void delete(String sportActivityIdentifier);
}
