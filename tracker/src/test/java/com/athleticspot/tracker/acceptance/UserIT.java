package com.athleticspot.tracker.acceptance;

import com.athleticspot.tracker.TrackerApplication;
import com.athleticspot.tracker.application.TrackerUserService;
import com.athleticspot.tracker.domain.model.TrackerUser;
import com.athleticspot.tracker.domain.model.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

    @Test
    public void whenThereIsNoUserThenEmpyUserListIsReturned(){
        //when
        final List<TrackerUser> allUsers = userRepository.findAllUsers();
        //then
        Assertions.assertThat(allUsers).isNotNull();
        Assertions.assertThat(allUsers).isEmpty();
    }

    @Test
    public void whenThereAreNoUserSetUu(){
        trackerUserService.getTimelineIdentifier();
    }
}
