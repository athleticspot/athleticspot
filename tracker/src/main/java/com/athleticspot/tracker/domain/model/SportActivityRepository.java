package com.athleticspot.tracker.domain.model;

import java.util.List;
import java.util.Optional;

/**
 * @author Tomasz Kasprzycki
 */
public interface SportActivityRepository {

    void store(SportActivity sportActivity);

    String nextSportActivityId();

    void delete(String sportActivityIdentifier);

    List<SportActivity> findByTimelineId(String timelineIdentifier);

    Optional<SportActivity> findBySportActivityId(String sportActivityId);
}
