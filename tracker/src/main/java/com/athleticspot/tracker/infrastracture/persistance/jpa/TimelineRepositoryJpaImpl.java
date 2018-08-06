package com.athleticspot.tracker.infrastracture.persistance.jpa;

import com.athleticspot.tracker.domain.model.Timeline;
import com.athleticspot.tracker.domain.model.TimelineRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author Tomasz Kasprzycki
 */
@Repository
public class TimelineRepositoryJpaImpl implements TimelineRepository {

    @Override
    public String nextTimelineId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public void store(Timeline timeline) {

    }

    @Override
    public Timeline find(String timelineIdentifier) {
        return null;
    }
}
