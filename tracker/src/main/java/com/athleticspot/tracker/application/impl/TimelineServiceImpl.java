package com.athleticspot.tracker.application.impl;

import com.athleticspot.common.SecurityUtils;
import com.athleticspot.tracker.application.ApplicationEvents;
import com.athleticspot.tracker.application.TimelineService;
import com.athleticspot.tracker.application.TrackerUserService;
import com.athleticspot.tracker.domain.model.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Tomasz Kasprzycki
 */
@Service
@Transactional
public class TimelineServiceImpl implements TimelineService {

    private final TimelineRepository timelineRepository;
    private final TrackerUserService trackerUserService;
    private final SportActivityRepository sportActivityRepository;
    private final ApplicationEvents applicationEvents;
    private final StravaApplicationServiceImpl stravaApplicationService;

    public TimelineServiceImpl(TimelineRepository timelineRepository,
                               TrackerUserService trackerUserService,
                               SportActivityRepository sportActivityRepository,
                               ApplicationEvents applicationEvents, StravaApplicationServiceImpl stravaApplicationService) {
        this.timelineRepository = timelineRepository;
        this.trackerUserService = trackerUserService;
        this.sportActivityRepository = sportActivityRepository;
        this.applicationEvents = applicationEvents;
        this.stravaApplicationService = stravaApplicationService;
    }

    @Override
    public String createTimeline() {
        final Timeline timeline = Timeline.create(timelineRepository.nextTimelineId());
        timelineRepository.store(timeline);
        applicationEvents.timelineWasCreated(timeline);
        return timeline.timelineIdentifier();
    }

    @Override
    public List<ManualSportActivity> getSportActivities() {
        final String timelineIdentifier = trackerUserService.getTimelineIdentifier();
        final List<ManualSportActivity> sportActivities = sportActivityRepository.findByTimelineId(timelineIdentifier);
        sportActivities.addAll(stravaApplicationService.getStravaActivities());
        return sportActivities;
    }

    @Override
    public void createTimelineWithActivities() {

    }

    @Override
    public String addActivity(SportActivityDetails sportActivityDetails, String activitySource) {
        final String timelineIdentifier = trackerUserService.getTimelineIdentifier();
        final String sportActivityIdentifier = sportActivityRepository.nextSportActivityId();
        ManualSportActivity manualSportActivity =
            ManualSportActivity.create(
                sportActivityIdentifier,
                activitySource,
                sportActivityDetails

            );
        //TODO: if timeline identifier is null then we need to assign it back to user
        Optional<Timeline> timelineOptional = timelineRepository.find(timelineIdentifier);

        Timeline timeline;
        if (!timelineOptional.isPresent()) {
            final String availableTimelineId = timelineRepository.nextTimelineId();
            timeline = Timeline.create(availableTimelineId);
            manualSportActivity.assignToTimeline(timeline.timelineIdentifier());
            trackerUserService.addTimelineIdentifier(availableTimelineId);
        } else {
            timeline = timelineOptional.get();
            manualSportActivity.assignToTimeline(timelineIdentifier);
        }
        timelineRepository.store(timeline);
        sportActivityRepository.store(manualSportActivity);
        applicationEvents.manualSportActivityAdded(manualSportActivity, SecurityUtils.getCurrentUserLogin());
        return sportActivityIdentifier;
    }

    @Override
    public void addActivities() {
        //TODO: future state
    }

    @Override
    public void removeActivity(ManualSportActivity manualSportActivity) {
        final String timelineIdentifier = trackerUserService.getTimelineIdentifier();
        Optional<Timeline> timeline = timelineRepository.find(timelineIdentifier);
        if (!timeline.isPresent()) {
            throw new IllegalStateException("Cannot remove sport activity when timeline doesn't exists");
        }
        timeline.get().removeTimelineEvent(manualSportActivity.identifier());
        sportActivityRepository.delete(manualSportActivity.identifier());
        timelineRepository.store(timeline.get());
    }

    @Override
    public void removeActivities() {
        //TODO: future state
    }

    private String checkUserTimelineIdentifier() {
        final String timelineIdentifier = trackerUserService.getTimelineIdentifier();
        if (Objects.isNull(timelineIdentifier)) {
            return createTimeline();
        }
        return timelineIdentifier;
    }
}
