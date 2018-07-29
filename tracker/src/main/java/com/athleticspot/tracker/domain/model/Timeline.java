package com.athleticspot.tracker.domain.model;

import com.google.common.collect.Lists;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author Tomasz Kasprzycki
 */
public class Timeline {

    private String timelineIdentifier;
    private final ApplicationUserId applicationUserId;
    private List<TimelineEvent> timelineEvents;

    private Timeline(ApplicationUserId applicationUserId, String timelineIdentifier) {
        Assert.notNull(timelineIdentifier, "Timeline identifier cannot be null");
        Assert.notNull(applicationUserId, "Application user identifier cannot be null");
        this.applicationUserId = applicationUserId;
        this.timelineIdentifier = timelineIdentifier;
        this.timelineEvents = Lists.newArrayList();
    }

    public static Timeline create(ApplicationUserId applicationUserId, String timelineIdentifier) {
        return new Timeline(applicationUserId, timelineIdentifier);
    }

    public static Timeline createWitActivities(String timelineIdentifier, ApplicationUserId mockApplicationUserId, List<TimelineEvent> timelineEvents) {
        Timeline timeline = new Timeline(mockApplicationUserId, timelineIdentifier);
        timeline.timelineEvents = timelineEvents;
        return timeline;

    }

    public ApplicationUserId user() {
        return applicationUserId;
    }

    public String timelineIdentifier() {
        return this.timelineIdentifier;
    }

    public void addTimelineEvent(TimelineEvent timelineEvent) {
        this.timelineEvents.add(timelineEvent);
    }

    public List<TimelineEvent> timelineEvents() {
        return this.timelineEvents;

    }
}
