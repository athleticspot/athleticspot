package com.athleticspot.tracker.acceptance;

import com.athleticspot.tracker.TrackerApplication;
import com.athleticspot.tracker.application.TrackerUserService;
import com.athleticspot.tracker.domain.model.TrackerUser;
import com.athleticspot.tracker.domain.model.TrackerUserRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
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
    TrackerUserRepository trackerUserRepository;

    @Autowired
    TrackerUserService trackerUserService;


    @Before
    public void setUp() {
        TrackerUser trackerUser = new TrackerUser("admin");
        trackerUserRepository.saveTrackerUser(trackerUser);
    }

    @Test
    public void whenThereIsNoUserThenEmpyUserListIsReturned() {

        //when
        List<TrackerUser> allUsers = trackerUserRepository.findAllUsers();
        //then
        Assertions.assertThat(allUsers).isNotNull();
        Assertions.assertThat(allUsers).hasSize(1);
        final TrackerUser savedUser = allUsers.get(0);

        Assertions.assertThat(allUsers).hasSize(1);
    }

}
