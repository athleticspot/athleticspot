package com.athleticspot.tracker.application;

import com.athleticspot.tracker.application.impl.TimelineServiceImpl;
import com.athleticspot.tracker.domain.model.ApplicationUserId;
import com.athleticspot.tracker.domain.model.Timeline;
import com.athleticspot.tracker.domain.model.TimelineRepository;
import com.athleticspot.tracker.domain.model.UserRepository;
import org.assertj.core.api.Assertions;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import java.util.UUID;

import static org.junit.Assert.*;

/**
 * @author Tomasz Kasprzycki
 */
public class TimelineServiceTest {

    private TimelineService timelineService;
    private UserRepository userRepository;
    private TimelineRepository timelineRepository;

    @Before
    public void setUp() {
        timelineRepository = Mockito.mock(TimelineRepository.class);
        userRepository = Mockito.mock(UserRepository.class);
        timelineService = new TimelineServiceImpl(timelineRepository, userRepository);
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

        //TODO: add assertion for event emit
    }

    @Test
    public void whenSystemAddActivityAndTimelineDoesntExisitNewTimelineIsCreated() {

    }

}
