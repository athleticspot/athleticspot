package com.athleticspot.tracker.domain.model;

import com.google.common.collect.Lists;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import static com.athleticspot.tracker.shared.TimelineEventFactory.testSportActivity;

/**
 * @author Tomasz Kasprzycki
 */
public class TimelineTest {

    private final String sportActivitySource = "Manual";
    private final SportActivityDetails sportActivityDetails = testSportActivity();

    @Test
    public void whenThereIsNoTimelineNewOneIsCreated() {
        //given
        String timelineIdentifier = UUID.randomUUID().toString();

        //when
        Timeline timeline = Timeline.create(timelineIdentifier);

        //then:
        Assertions.assertThat(timeline.timelineIdentifier()).isEqualTo(timelineIdentifier);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNullTimelieneIdentiefierIsPassedToTimelineConstructorThenObjectIsNotCreated() {
        //given
        String timelineIdentifier = null;

        Timeline.create(timelineIdentifier);
    }

    @Test
    public void whenNewEventIsCreatedItIsAddedToTimeline() {
        //given
        String timelineIdentifier = UUID.randomUUID().toString();
        Timeline timeline = Timeline.create(timelineIdentifier);
        String sportActivityIdentifier = UUID.randomUUID().toString();
        TimelineEvent sportActivity = SportActivity.create(sportActivityIdentifier, sportActivitySource, sportActivityDetails);

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
        List<TimelineEvent> timelineEvents = testActivities(5);
        String timelineIdentifier = UUID.randomUUID().toString();

        //when
        Timeline timeline = Timeline.createWitActivities(timelineIdentifier, timelineEvents);

        Assertions.assertThat(timeline.timelineIdentifier()).isEqualTo(timelineIdentifier);
        Assertions.assertThat(timeline.timelineEvents()).hasSize(5);
    }

    @Test
    public void whenActivityIsRemovedFromTimelineThenTimelineDoesntContainItLonger() {
        //given
        final Timeline timeline = testTimeline();
        final String sportActivityIdentifier = UUID.randomUUID().toString();
        timeline.addTimelineEvent(SportActivity.create(sportActivityIdentifier, sportActivitySource, sportActivityDetails));
        Assertions.assertThat(timeline.timelineEvents()).hasSize(1);

        //when
        timeline.removeTimelineEvent(sportActivityIdentifier);

        //then
        Assertions.assertThat(timeline.timelineEvents()).hasSize(0);
    }

    @Test
    public void whenActivitiesAreAddedInBulkThenTimelineContainsAllOfThem() {
        //given
        final Timeline timeline = testTimeline();
        final List testActivities = testActivities(5);
        Assertions.assertThat(timeline.timelineEvents()).hasSize(0);

        //when
        timeline.addTimelineEvents(testActivities);

        //then
        Assertions.assertThat(timeline.timelineEvents()).hasSize(5);
    }

    @Test
    public void whenActivitiesAreRemovedInBulkThenTimelineDoesntContainAnyOfThem() {
        //given
        final Timeline timeline = testTimeline();
        final List testActivities = testActivities(10);
        timeline.addTimelineEvents(testActivities);
        Assertions.assertThat(timeline.timelineEvents()).hasSize(10);
        final TimelineEvent leftTimlineEvent = (TimelineEvent) testActivities.remove(0);

        //when
        timeline.removeTimelineEvents(testActivities);

        Assertions.assertThat(timeline.timelineEvents()).hasSize(1);
        Assertions.assertThat(timeline.timelineEvents()).contains(leftTimlineEvent);
    }

    private List<TimelineEvent> testActivities(int activitiesSize) {
        List<TimelineEvent> timelineEvents = Lists.newArrayList();
        for (int i = 0; i < activitiesSize; i++) {
            timelineEvents.add(SportActivity.create(UUID.randomUUID().toString(), sportActivitySource, sportActivityDetails));
        }
        return timelineEvents;
    }

    private Timeline testTimeline() {
        String timelineIdentifier = UUID.randomUUID().toString();
        return Timeline.create(timelineIdentifier);
    }

}
