package com.athleticspot.tracker.application;

import com.athleticspot.tracker.application.impl.TimelineServiceImpl;
import com.athleticspot.tracker.domain.model.*;
import com.athleticspot.tracker.shared.TestTimelineFactory;
import com.athleticspot.tracker.shared.TimelineEventFactory;
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
    private SportActivityRepository sportActivityRepository;
    private ApplicationEvents applicationEvents;

    @Before
    public void setUp() {
        timelineRepository = Mockito.mock(TimelineRepository.class);
        userRepository = Mockito.mock(UserRepository.class);
        applicationEvents = Mockito.mock(ApplicationEvents.class);
        sportActivityRepository = Mockito.mock(SportActivityRepository.class);
        timelineService = new TimelineServiceImpl(timelineRepository, userRepository, sportActivityRepository, applicationEvents);
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

        Mockito.verify(timelineRepository, Mockito.times(1)).nextTimelineId();
        Mockito.verify(timelineRepository, Mockito.times(1)).store(Matchers.isA(Timeline.class));
        Mockito.verify(userRepository, Mockito.times(1)).getCurrentUserId();
        Mockito.verify(applicationEvents, Mockito.times(1)).timelineWasCreated(Matchers.isA(Timeline.class));
    }

    @Test
    public void whenActivityIsAddedToTimelineThenItIsStored() {
        //given
        final String timelineIdentifier = UUID.randomUUID().toString();
        final String expectedSportActivityIdentifier = UUID.randomUUID().toString();
        Timeline timeline = TestTimelineFactory.testTimeline(timelineIdentifier);
        Mockito.when(userRepository.getTimelineIdentifier()).thenReturn(timelineIdentifier);
        Mockito.when(sportActivityRepository.nextSportActivityId()).thenReturn(expectedSportActivityIdentifier);
        Mockito.when(timelineRepository.find(timelineIdentifier)).thenReturn(timeline);
        SportActivity sportActivity = SportActivity.create(
            UUID.randomUUID().toString(),
            "Manual",
            TimelineEventFactory.testSportActivity()
        );

        //when
        timelineService.addActivity(sportActivity);

        //then
        Assertions.assertThat(timeline.timelineEvents()).containsExactly(sportActivity);
        Mockito.verify(timelineRepository, Mockito.times(1)).find(timelineIdentifier);
        Mockito.verify(sportActivityRepository, Mockito.times(1)).store(sportActivity);
        Mockito.verify(applicationEvents, Mockito.times(1)).sportActivityAdded(sportActivity);

    }

    @Test
    public void whenSystemAddActivityAndTimelineDoesntExisitNewTimelineIsCreated() {

    }

}
