package com.athleticspot.tracker.infrastracture.persistance;

import com.athleticspot.tracker.domain.model.Timeline;
import com.athleticspot.tracker.domain.model.TimelineRepository;

import java.util.UUID;

/**
 * @author Tomasz Kasprzycki
 */
public class TimelineRepositoryImpl implements TimelineRepository {

    @Override
    public String nextTimelineId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public void store(Timeline timeline) {

    }
}
