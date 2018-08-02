package com.athleticspot.tracker.application;

import com.athleticspot.tracker.application.impl.TimelineServiceImpl;
import com.athleticspot.tracker.domain.model.*;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import java.util.UUID;

/**
 * @author Tomasz Kasprzycki
 */
public class TimelineServiceTest {

    private TimelineService timelineService;
    private UserRepository userRepository;
    private TimelineRepository timelineRepository;
    private ApplicationEvents applicationEvents;

    @Before
    public void setUp() {
        timelineRepository = Mockito.mock(TimelineRepository.class);
        userRepository = Mockito.mock(UserRepository.class);
        applicationEvents = Mockito.mock(ApplicationEvents.class);
        timelineService = new TimelineServiceImpl(timelineRepository, userRepository, applicationEvents);
    }

    @Test
    public void whenTimelineIsCreatedThenItIsStored() {
        //given
        final String expectedTimelineId = UUID.randomUUID().toString();
        final ApplicationUserId mockUser = ApplicationUserId.create(UUID.randomUUID().toString());
        Mockito.when(timelineRepository.nextTimelineId()).thenReturn(expectedTimelineId);
        Mockito.when(userRepository.getCurrentUserId()).thenReturn(mockUser);

        //when
        final String timelineId = timelineService.createTimeline();

        //then
        Assertions.assertThat(timelineId).isEqualTo(expectedTimelineId);

        Mockito.verify(timelineRepository,Mockito.times(1)).nextTimelineId();
        Mockito.verify(timelineRepository,Mockito.times(1)).store(Matchers.isA(Timeline.class));
        Mockito.verify(userRepository,Mockito.times(1)).getCurrentUserId();
        Mockito.verify(applicationEvents,Mockito.times(1)).timelineWasCreated();

        //TODO: add assertion for event emit
    }

    @Test
    public void whenSystemAddActivityAndTimelineDoesntExisitNewTimelineIsCreated() {

    }

}
