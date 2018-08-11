package com.athleticspot.tracker.domain.model;

import com.athleticspot.tracker.domain.shared.Entity;
import com.google.common.collect.Lists;
import org.springframework.util.Assert;

import javax.persistence.*;
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

    @Column
    private String timelineIdentifier;

    @Transient
    private List<TimelineEvent> timelineEvents;

    private Timeline(String timelineIdentifier) {
        Assert.notNull(timelineIdentifier, "Timeline identifier cannot be null");
        this.timelineIdentifier = timelineIdentifier;
        this.timelineEvents = Lists.newArrayList();
    }

    public static Timeline create(String timelineIdentifier) {
        return new Timeline(timelineIdentifier);
    }

    public static Timeline createWitActivities(String timelineIdentifier, List<TimelineEvent> timelineEvents) {
        Timeline timeline = new Timeline(timelineIdentifier);
        timeline.timelineEvents = timelineEvents;
        return timeline;
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
