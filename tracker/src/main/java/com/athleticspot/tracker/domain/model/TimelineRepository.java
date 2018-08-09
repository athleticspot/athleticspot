package com.athleticspot.tracker.domain.model;

import java.util.Optional;

/**
 * @author Tomasz Kasprzycki
 */
public interface TimelineRepository {

    String nextTimelineId();

    void store(Timeline timeline);

    Optional<Timeline> find(String timelineIdentifier);
}
