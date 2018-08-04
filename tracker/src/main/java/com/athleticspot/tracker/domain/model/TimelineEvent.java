package com.athleticspot.tracker.domain.model;

import java.time.LocalDateTime;

/**
 * @author Tomasz Kasprzycki
 */
public interface TimelineEvent {

    String identifier();

    void assignToTimeline(String timelineIdentifier);
}

