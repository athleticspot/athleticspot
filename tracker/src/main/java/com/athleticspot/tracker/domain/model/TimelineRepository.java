package com.athleticspot.tracker.domain.model;

/**
 * @author Tomasz Kasprzycki
 */
public interface TimelineRepository {

    String nextTimelineId();

    void store(Timeline timeline);
}
