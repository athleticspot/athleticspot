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

import java.util.Optional;
import java.util.UUID;

/**
 * @author Tomasz Kasprzycki
 */
public class TimelineServiceTest {

    private TimelineService timelineService;
    private TrackerUserService trackerUserService;
    private TimelineRepository timelineRepository;
    private SportActivityRepository sportActivityRepository;
    private ApplicationEvents applicationEvents;
    private Timeline timeline;
    final String expectedTimelineId = UUID.randomUUID().toString();


    @Before
    public void setUp() {
        timelineRepository = Mockito.mock(TimelineRepository.class);
        trackerUserService = Mockito.mock(TrackerUserService.class);
        applicationEvents = Mockito.mock(ApplicationEvents.class);
        sportActivityRepository = Mockito.mock(SportActivityRepository.class);
        timelineService = new TimelineServiceImpl(timelineRepository, trackerUserService, sportActivityRepository, applicationEvents);

        timeline = TestTimelineFactory.testTimeline(expectedTimelineId);
    }

    @Test
    public void whenTimelineIsCreatedThenItIsStored() {
        //given
        Mockito.when(timelineRepository.nextTimelineId()).thenReturn(expectedTimelineId);

        //when
        final String timelineId = timelineService.createTimeline();

        //then
        Assertions.assertThat(timelineId).isEqualTo(expectedTimelineId);

        Mockito.verify(timelineRepository, Mockito.times(1)).nextTimelineId();
        Mockito.verify(timelineRepository, Mockito.times(1)).store(Matchers.isA(Timeline.class));
        Mockito.verify(applicationEvents, Mockito.times(1)).timelineWasCreated(Matchers.isA(Timeline.class));
    }

    @Test
    public void whenActivityIsAddedToTimelineThenItIsStored() {
        //given
        final String expectedSportActivityIdentifier = UUID.randomUUID().toString();
        Mockito.when(trackerUserService.getTimelineIdentifier()).thenReturn(expectedTimelineId);
        Mockito.when(sportActivityRepository.nextSportActivityId()).thenReturn(expectedSportActivityIdentifier);
        Mockito.when(timelineRepository.find(expectedTimelineId)).thenReturn(Optional.of(timeline));
        SportActivity sportActivity = SportActivity.create(
            expectedSportActivityIdentifier,
            "Manual",
            TimelineEventFactory.testSportActivity()
        );

        //when
        timelineService.addActivity(TimelineEventFactory.testSportActivity(), "Manual");

        //then
        Assertions.assertThat(timeline.timelineEvents()).containsExactly(sportActivity);
        Mockito.verify(timelineRepository, Mockito.times(1)).find(expectedTimelineId);
        Mockito.verify(sportActivityRepository, Mockito.times(1)).store(sportActivity);
        Mockito.verify(applicationEvents, Mockito.times(1)).sportActivityAdded(sportActivity);
    }

    @Test
    public void whenSystemAddActivityAndTimelineDoesntExisitNewTimelineIsCreated() {
        //given
        final String expectedSportActivityIdentifier = UUID.randomUUID().toString();
        Mockito.when(trackerUserService.getTimelineIdentifier()).thenReturn(null);
        Mockito.when(timelineRepository.nextTimelineId()).thenReturn(expectedTimelineId);
        Mockito.when(timelineRepository.find(null)).thenReturn(Optional.empty());
        Mockito.when(sportActivityRepository.nextSportActivityId()).thenReturn(expectedSportActivityIdentifier);
        SportActivity sportActivity = SportActivity.create(
            expectedSportActivityIdentifier,
            "Manual",
            TimelineEventFactory.testSportActivity()
        );

        //when
        timelineService.addActivity(TimelineEventFactory.testSportActivity(), "Manual" );

        //then
        Mockito.verify(trackerUserService, Mockito.times(1)).getTimelineIdentifier();
        Mockito.verify(timelineRepository, Mockito.times(1)).store(timeline);
        Mockito.verify(sportActivityRepository, Mockito.times(1)).store(sportActivity);
        Mockito.verify(applicationEvents, Mockito.times(1)).sportActivityAdded(sportActivity);
    }

    @Test
    public void whenActivityIsRemovedFromTimelineThenTimelineDoestHaveIt() {
        //given
        Mockito.when(trackerUserService.getTimelineIdentifier()).thenReturn(expectedTimelineId);
        Mockito.when(timelineRepository.find(expectedTimelineId)).thenReturn(Optional.of(timeline));
        final String sportActivityIdentifier = UUID.randomUUID().toString();
        SportActivity sportActivity = SportActivity.create(
            sportActivityIdentifier,
            "Manual",
            TimelineEventFactory.testSportActivity()
        );
        timeline.addTimelineEvent(sportActivity);

        //when
        timelineService.removeActivity(sportActivity);

        //then
        Assertions.assertThat(timeline.timelineEvents()).hasSize(0);

        Mockito.verify(trackerUserService, Mockito.times(1)).getTimelineIdentifier();
        Mockito.verify(sportActivityRepository, Mockito.times(1)).delete(sportActivityIdentifier);
        Mockito.verify(timelineRepository, Mockito.times(1)).store(timeline);
    }

}
