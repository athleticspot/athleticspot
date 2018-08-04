package com.athleticspot.tracker.application.impl;

import com.athleticspot.tracker.application.ApplicationEvents;
import com.athleticspot.tracker.application.TimelineService;
import com.athleticspot.tracker.domain.model.*;

/**
 * @author Tomasz Kasprzycki
 */
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

        final Timeline timeline = timelineRepository.find(userRepository.getTimelineIdentifier());
        //TODO: do we need below line? We can just simply save sport activity.
        timeline.addTimelineEvent(sportActivity);
        sportActivity.assignToTimeline(userRepository.getTimelineIdentifier());
        sportActivityRepository.store(sportActivity);
        applicationEvents.sportActivityAdded(sportActivity);
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
