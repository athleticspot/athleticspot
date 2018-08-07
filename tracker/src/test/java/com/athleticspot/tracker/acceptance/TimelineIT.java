package com.athleticspot.tracker.acceptance;

import com.athleticspot.tracker.application.TimelineService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Tomasz Kasprzycki
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfiguration.class)
public class TimelineIT {

    @Autowired
    TimelineService timelineService;

    @Test
    public void testTimeline(){
        Assertions.assertThat(true).isTrue();
    }
}
