package com.athleticspot.tracker.application.impl;

import com.athleticspot.tracker.application.ApplicationEvents;
import com.athleticspot.tracker.application.TimelineService;
import com.athleticspot.tracker.domain.model.Timeline;
import com.athleticspot.tracker.domain.model.TimelineRepository;
import com.athleticspot.tracker.domain.model.UserRepository;

/**
 * @author Tomasz Kasprzycki
 */
public class TimelineServiceImpl implements TimelineService {

    private final TimelineRepository timelineRepository;
    private final UserRepository userRepository;
    private final ApplicationEvents applicationEvents;

    public TimelineServiceImpl(TimelineRepository timelineRepository, UserRepository userRepository, ApplicationEvents applicationEvents) {
        this.timelineRepository = timelineRepository;
        this.userRepository = userRepository;
        this.applicationEvents = applicationEvents;
    }

    @Override
    public String createTimeline() {
        final Timeline timeline = Timeline.create(userRepository.getCurrentUserId(), timelineRepository.nextTimelineId());
        timelineRepository.store(timeline);
        applicationEvents.timelineWasCreated();
        return timeline.timelineIdentifier();
    }

    @Override
    public void addActivity() {

    }

    @Override
    public void addActivities() {

    }

    @Override
    public void removeActivity() {

    }

    @Override
    public void removeActivities() {

    }
}
