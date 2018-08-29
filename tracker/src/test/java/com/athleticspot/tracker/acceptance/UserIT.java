package com.athleticspot.tracker.acceptance;

import com.athleticspot.tracker.TrackerApplication;
import com.athleticspot.tracker.application.TrackerUserService;
import com.athleticspot.tracker.domain.model.TrackerUser;
import com.athleticspot.tracker.domain.model.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Tomasz Kasprzycki
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TrackerApplication.class)
@Transactional
public class UserIT {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TrackerUserService trackerUserService;

    private final String timelineId = "timelineId";

    @Before
    public void setUp() {
        TrackerUser trackerUser = new TrackerUser("admin", timelineId);
        userRepository.saveTrackerUser(trackerUser);
    }

    @Test
    public void whenThereIsNoUserThenEmpyUserListIsReturned() {
        //when
        List<TrackerUser> allUsers = userRepository.findAllUsers();
        //then
        Assertions.assertThat(allUsers).isNotNull();
        Assertions.assertThat(allUsers).hasSize(1);
        final TrackerUser trackerUser = allUsers.get(0);
        final String newTimeId = "newTimeId";
        trackerUser.assignTimelineIdentifier(newTimeId);
        userRepository.saveTrackerUser(trackerUser);

        allUsers = userRepository.findAllUsers();
        Assertions.assertThat(allUsers).hasSize(1);
        Assertions.assertThat(allUsers.get(0).getTimelineIdentifier()).isEqualTo(newTimeId);
    }

    @Test
    public void whenThereAreNoUserSetUu() {
        //given
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(new UsernamePasswordAuthenticationToken("admin", "admin"));
        SecurityContextHolder.setContext(securityContext);

        //when
        final String timelineIdentifier = trackerUserService.getTimelineIdentifier();

        //then
        Assertions.assertThat(timelineIdentifier).isEqualTo(timelineId);
    }
}
