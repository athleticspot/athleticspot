package com.athleticspot.tracker.domain.model;

import com.google.common.collect.Lists;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

/**
 * @author Tomasz Kasprzycki
 */
public class TimelineTest {

    @Test
    public void whenThereIsNoTimelineNewOneIsCreated() {
        //given
        String timelineIdentifier = UUID.randomUUID().toString();
        ApplicationUserId mockApplicationUserId = ApplicationUserId.create(UUID.randomUUID().toString());

        //when
        Timeline timeline = Timeline.create(mockApplicationUserId, timelineIdentifier);

        //then:
        Assertions.assertThat(timeline.user()).isNotNull();
        Assertions.assertThat(timeline.timelineIdentifier()).isEqualTo(timelineIdentifier);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNullTimelieneIdentiefierIsPassedToTimelineConstructorThenObjectIsNotCreated() {
        //given
        String timelineIdentifier = null;
        ApplicationUserId mockApplicationUserId = ApplicationUserId.create(UUID.randomUUID().toString());

        Timeline.create(mockApplicationUserId, timelineIdentifier);
    }


    @Test(expected = IllegalArgumentException.class)
    public void whenNullApplicationIdentiefierIsPassedToTimelineConstructorThenObjectIsNotCreated() {
        //given
        String timelineIdentifier = UUID.randomUUID().toString();
        ApplicationUserId mockApplicationUserId = null;

        Timeline.create(mockApplicationUserId, timelineIdentifier);
    }

    @Test
    public void whenNewEventIsCreatedItIsAddedToTimeline() {
        //given
        String timelineIdentifier = UUID.randomUUID().toString();
        ApplicationUserId mockApplicationUserId = ApplicationUserId.create(UUID.randomUUID().toString());
        Timeline timeline = Timeline.create(mockApplicationUserId, timelineIdentifier);
        String sportActivityIdentifier = UUID.randomUUID().toString();
        TimelineEvent sportActivity = new SportActivity(sportActivityIdentifier);

        //when
        timeline.addTimelineEvent(sportActivity);

        //then
        Assertions.assertThat(timeline.timelineEvents()).hasSize(1);
        Assertions.assertThat(timeline.timelineEvents().get(0).identifier())
            .isEqualTo(sportActivityIdentifier);
    }

    @Test
    public void whenActivitiesExistTimelineIsCreatedWithActivities() {
        //given
        List timelineEvents = testActivities(5);
        String timelineIdentifier = UUID.randomUUID().toString();
        ApplicationUserId mockApplicationUserId = ApplicationUserId.create(UUID.randomUUID().toString());

        //when
        Timeline timeline = Timeline.createWitActivities(timelineIdentifier, mockApplicationUserId, timelineEvents);

        Assertions.assertThat(timeline.timelineIdentifier()).isEqualTo(timelineIdentifier);
        Assertions.assertThat(timeline.user()).isEqualTo(mockApplicationUserId);
        Assertions.assertThat(timeline.timelineEvents()).hasSize(5);
    }

    @Test
    public void whenActivityIsRemovedFromTimelineThenTimelineDoesntContainItLonger() {

    }

    public void whenActivitiesAreAddedInBulkThenTimelineContainsAllOfThem() {

    }

    public void whenActivitiesAreRemovedInBulkThenTimelineDoesntContainAnyOfThem() {

    }

    private List testActivities(int activitiesSize) {
        List<TimelineEvent> timelineEvents = Lists.newArrayList();
        for (int i = 0; i < activitiesSize; i++) {
            timelineEvents.add(new SportActivity(UUID.randomUUID().toString()));
        }
        return timelineEvents;
    }
}
