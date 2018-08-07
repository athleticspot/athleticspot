package com.athleticspot.tracker.application.impl;

import com.athleticspot.tracker.application.ApplicationEvents;
import com.athleticspot.tracker.application.TimelineService;
import com.athleticspot.tracker.domain.model.*;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author Tomasz Kasprzycki
 */
@Service
public class TimelineServiceImpl implements TimelineService {

    private final TimelineRepository timelineRepository;
    private final UserRepository userRepository;
    private final SportActivityRepository sportActivityRepository;
    private final ApplicationEvents applicationEvents;

    public TimelineServiceImpl(TimelineRepository timelineRepository, UserRepository userRepository, SportActivityRepository sportActivityRepository, ApplicationEvents applicationEvents) {
        this.timelineRepository = timelineRepository;
        this.userRepository = userRepository;
        this.sportActivityRepository = sportActivityRepository;
        this.applicationEvents = applicationEvents;
    }

    @Override
    public String createTimeline() {
        final Timeline timeline = Timeline.create(userRepository.getCurrentUserId(), timelineRepository.nextTimelineId());
        timelineRepository.store(timeline);
        applicationEvents.timelineWasCreated(timeline);
        return timeline.timelineIdentifier();
    }

    @Override
    public void createTimelineWithActivities() {

    }

    @Override
    public void addActivity(SportActivity sportActivity) {

        final String timelineIdentifier = userRepository.getTimelineIdentifier();
        Timeline timeline = timelineRepository.find(timelineIdentifier);
        if (Objects.isNull(timeline)) {
            timeline = Timeline.create(userRepository.getCurrentUserId(), timelineRepository.nextTimelineId());
            timelineRepository.store(timeline);
            sportActivity.assignToTimeline(timeline.timelineIdentifier());
        } else {
            sportActivity.assignToTimeline(timelineIdentifier);
        }
        //TODO: do we need below line? We can just simply save sport activity.
        timeline.addTimelineEvent(sportActivity);
        sportActivityRepository.store(sportActivity);
        applicationEvents.sportActivityAdded(sportActivity);
    }

    @Override
    public void addActivities() {
        //TODO: future state
    }

    @Override
    public void removeActivity(SportActivity sportActivity) {
        final String timelineIdentifier = userRepository.getTimelineIdentifier();
        Timeline timeline = timelineRepository.find(timelineIdentifier);
        if (Objects.isNull(timeline)) {
            throw new IllegalStateException("Cannot remove sport activity when timeline doesn't exists");
        }
        timeline.removeTimelineEvent(sportActivity.identifier());
        sportActivityRepository.delete(sportActivity.identifier());
        timelineRepository.store(timeline);
    }

    @Override
    public void removeActivities() {
        //TODO: future state
    }
}
