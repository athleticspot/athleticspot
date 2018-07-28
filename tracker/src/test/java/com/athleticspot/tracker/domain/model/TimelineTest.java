package com.athleticspot.tracker.domain.model;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.UUID;

/**
 * @author Tomasz Kasprzycki
 */
public class TimelineTest {

    @Test
    public void whenThereIsNoTimelineNewOneIsCreated(){
        String timelineIdentifier = UUID.randomUUID().toString();
        ApplicationUserId mockApplicationUserId = ApplicationUserId.create(UUID.randomUUID().toString());
        Timeline timeline = Timeline.create(mockApplicationUserId, timelineIdentifier);

        Assertions.assertThat(timeline.user()).isNotNull();
        Assertions.assertThat(timeline.timelineIdentifier()).isEqualTo(timelineIdentifier);
    }

    @Test
    public void whenWrondParametersArePassedToTimelineConstructorThenObjectIsNotCreated(){

    }

    public void whenNewTimelineIsCreatedThenItIsAssignedForTheUser(){

    }

    public void whenNewEventIsCreatedItIsAddedToTimeline(){

    }
}
