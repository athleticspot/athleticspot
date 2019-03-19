package com.athleticspot.tracker.acceptance;

import com.athleticspot.tracker.TrackerApplication;
import com.athleticspot.tracker.application.TimelineService;
import com.athleticspot.tracker.domain.model.*;
import com.athleticspot.tracker.domain.model.manual.ManualSportActivityRepository;
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
@SpringBootTest(classes = TrackerApplication.class, properties="spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.data.neo4j.Neo4jDataAutoConfiguration")
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

    @Autowired
    private GenericSportActivityRepository genericSportActivityRepository;

    @MockBean
    private UserRepository userRepository;

    private final String expectedTimelineIdentifier = UUID.randomUUID().toString();
    private final String userLogin = "admin";

    @Before
    public void setUp() {
        genericSportActivityRepository.deleteAll();
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


}
