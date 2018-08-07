package com.athleticspot.tracker.domain.model;

import com.athleticspot.tracker.domain.shared.Entity;
import com.google.common.collect.Lists;
import org.springframework.util.Assert;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Aggregate root
 * @author Tomasz Kasprzycki
 */
@javax.persistence.Entity
public class Timeline implements Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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

    public void addTimelineEvents(List activities) {
        this.timelineEvents.addAll(activities);
    }

    public List<TimelineEvent> timelineEvents() {
        return this.timelineEvents;

    }

    public void removeTimelineEvent(String sportActivityIdentifier) {
        this.timelineEvents
            = this.timelineEvents
            .stream()
            .filter(timelineEvent -> !timelineEvent.identifier().equals(sportActivityIdentifier))
            .collect(Collectors.toList());
    }

    public void removeTimelineEvents(List testActivities) {
        this.timelineEvents
            = this.timelineEvents.stream()
            .filter(timelineEvent -> !testActivities.contains(timelineEvent))
            .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Timeline)) return false;
        Timeline timeline = (Timeline) o;
        return Objects.equals(timelineIdentifier, timeline.timelineIdentifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timelineIdentifier);
    }
}
