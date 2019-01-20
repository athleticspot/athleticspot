package com.athleticspot.tracker.acceptance;

import com.athleticspot.tracker.domain.graph.SportActivity;
import com.athleticspot.tracker.domain.graph.SportActivityRepository;
import com.athleticspot.tracker.domain.model.SportActivityType;
import com.athleticspot.tracker.domain.model.TrackerSource;
import javastrava.api.v3.model.StravaMapPoint;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

/**
 * @author Tomasz Kasprzycki
 */
@RunWith(SpringRunner.class)
@DataNeo4jTest
public class SportActivityAcceptanceTest {

    @Autowired
    SportActivityRepository sportActivityRepository;

    @Test
    public void sportActivitySanityTest() {
        final TrackerSource trackerSource = TrackerSource.STRAVA;
        final SportActivityType sportActivityType = SportActivityType.RUN;
        final int trackingSystemId = 111;
        final String externalId = "9999";
        final float distance = 12F;
        final LocalDateTime startDate = LocalDateTime.now();
        final StravaMapPoint startLatlng = new StravaMapPoint(10F, 11F);
        SportActivity sportActivity = SportActivity.create(
            trackerSource,
            sportActivityType,
            trackingSystemId,
            externalId,
            distance,
            startDate,
            startLatlng
        );

        sportActivityRepository.save(sportActivity);

        final Iterable<SportActivity> sportActivities = sportActivityRepository.findAll();
        Assertions.assertThat(sportActivities).hasSize(1);
        final SportActivity savedSportActivity = sportActivities.iterator().next();

        Assertions.assertThat(savedSportActivity.getTrackerSource()).isEqualTo(trackerSource);
        Assertions.assertThat(savedSportActivity.getSportActivityType()).isEqualTo(sportActivityType);
        Assertions.assertThat(savedSportActivity.getTrackingSystemId()).isEqualTo(trackingSystemId);
        Assertions.assertThat(savedSportActivity.getExternalId()).isEqualTo(externalId);
        Assertions.assertThat(savedSportActivity.getDistance()).isEqualTo(distance);
        Assertions.assertThat(savedSportActivity.getStartDate()).isEqualTo(startDate);
    }
}
