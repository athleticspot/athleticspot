package com.athleticspot.tracker.acceptance;

import com.athleticspot.tracker.TrackerApplication;
import com.athleticspot.tracker.application.TimelineService;
import com.athleticspot.tracker.domain.model.ApplicationUserId;
import com.athleticspot.tracker.domain.model.Timeline;
import com.athleticspot.tracker.domain.model.TimelineRepository;
import com.athleticspot.tracker.domain.model.UserRepository;
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

    @MockBean
    UserRepository userRepository;

    private final String expectedTimelineIdentifier = UUID.randomUUID().toString();

    @Before
    public void setUp() {
        Mockito.when(userRepository.getTimelineIdentifier()).thenReturn(expectedTimelineIdentifier);
        Mockito.when(userRepository.getCurrentUserId()).thenReturn(ApplicationUserId.create(UUID.randomUUID().toString()));
    }

    @Test
    public void testTimeline() {
        //given

        //when
        final String expectedTimelineIdentifier = timelineService.createTimeline();

        //then
        final Optional<Timeline> timeline = timelineRepository.find(expectedTimelineIdentifier);
        Assertions.assertThat(timeline.get()).isNotNull();
    }
}
