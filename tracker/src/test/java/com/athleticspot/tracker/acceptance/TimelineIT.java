package com.athleticspot.tracker.acceptance;

import com.athleticspot.tracker.TrackerApplication;
import com.athleticspot.tracker.application.TimelineService;
import com.athleticspot.tracker.domain.model.Timeline;
import com.athleticspot.tracker.domain.model.TimelineRepository;
import com.athleticspot.tracker.domain.model.TrackerUser;
import com.athleticspot.tracker.domain.model.UserRepository;
import com.athleticspot.tracker.domain.model.manual.ManualSportActivity;
import com.athleticspot.tracker.domain.model.manual.ManualSportActivityDetails;
import com.athleticspot.tracker.domain.model.manual.ManualSportActivityRepository;
import com.athleticspot.tracker.shared.TestSportActivityDetailsFactory;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    private ManualSportActivityRepository manualSportActivityRepository;

    @MockBean
    private UserRepository userRepository;

    private final String expectedTimelineIdentifier = UUID.randomUUID().toString();
    private final String userLogin = "admin";

    @Before
    public void setUp() {
        Mockito.when(userRepository.getTimelineIdentifier(Matchers.any())).thenReturn(expectedTimelineIdentifier);
        Mockito.when(userRepository.getUser(Matchers.any())).thenReturn(
            new TrackerUser("admin", null)
        );
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
        final ManualSportActivityDetails expectedManualSportActivityDetails = TestSportActivityDetailsFactory.create();
        expectedSportActivityId = timelineService.addActivity(
            expectedManualSportActivityDetails,
            "Manual"
        );

        //then
        final Optional<ManualSportActivity> byTimelineId = manualSportActivityRepository.findBySportActivityId(expectedSportActivityId);
        Assertions.assertThat(byTimelineId.isPresent()).isTrue();
        Assertions.assertThat(byTimelineId.get().details()).isEqualTo(expectedManualSportActivityDetails);
        Assertions.assertThat(byTimelineId.get().source()).isEqualTo("Manual");
    }
}
