package com.athleticspot.tracker.domain.model.manual;

import java.util.List;
import java.util.Optional;

/**
 * @author Tomasz Kasprzycki
 */
public interface ManualSportActivityRepository {

    void store(ManualSportActivity manualSportActivity);

    String nextSportActivityId();

    void delete(String sportActivityIdentifier);

    List<ManualSportActivity> findByTimelineId(String timelineIdentifier);

    Optional<ManualSportActivity> findBySportActivityId(String sportActivityId);
}
