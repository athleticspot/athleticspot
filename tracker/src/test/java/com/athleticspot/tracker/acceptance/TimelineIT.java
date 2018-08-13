package com.athleticspot.tracker.acceptance;

import com.athleticspot.tracker.TrackerApplication;
import com.athleticspot.tracker.application.TimelineService;
import com.athleticspot.tracker.domain.model.*;
import com.athleticspot.tracker.shared.TestSportActivityDetailsFactory;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Tomasz Kasprzycki
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TrackerApplication.class)
@Transactional
public class TimelineIT {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private TimelineService timelineService;

    @Autowired
    private TimelineRepository timelineRepository;

    @Autowired
    private SportActivityRepository sportActivityRepository;

    @MockBean
    private UserRepository userRepository;

    private final String expectedTimelineIdentifier = UUID.randomUUID().toString();

    @Before
    public void setUp() {
        Mockito.when(userRepository.getTimelineIdentifier()).thenReturn(expectedTimelineIdentifier);
        Mockito.when(userRepository.getCurrentUserId()).thenReturn(ApplicationUserId.create(UUID.randomUUID().toString()));
    }

    @Test
    public void whenTimelineIsCreatedThenItsStored() {
        //given

        //when
        final String expectedTimelineIdentifier = timelineService.createTimeline();

        //then
        final Optional<Timeline> timeline = timelineRepository.find(expectedTimelineIdentifier);
        final Timeline timelineUnderTest = timeline.get();
        Assertions.assertThat(timelineUnderTest).isNotNull();
        Assertions.assertThat(timelineUnderTest.timelineIdentifier()).isEqualTo(expectedTimelineIdentifier);
    }

    @Test
    public void whenSportEventIsAddedToTimelineThenItsStored() {
        //given
        String expectedSportActivityId;

        //when
        final SportActivityDetails expectedSportActivityDetails = TestSportActivityDetailsFactory.create();
        expectedSportActivityId = timelineService.addActivity(
            expectedSportActivityDetails,
            "Manual"
        );

        //then
        final Optional<SportActivity> byTimelineId = sportActivityRepository.findBySportActivityId(expectedSportActivityId);
        Assertions.assertThat(byTimelineId.isPresent()).isTrue();
        Assertions.assertThat(byTimelineId.get().details()).isEqualTo(expectedSportActivityDetails);
        Assertions.assertThat(byTimelineId.get().source()).isEqualTo("Manual");
    }
}
