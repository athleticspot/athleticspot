package com.athleticspot.tracker.acceptance;

import com.athleticspot.tracker.domain.graph.Athlete;
import com.athleticspot.tracker.domain.graph.GraphAthleteRepository;
import com.athleticspot.tracker.domain.graph.SportActivity;
import com.athleticspot.tracker.domain.graph.SportActivityRepository;
import com.athleticspot.tracker.domain.model.MeasureSystemProvider;
import com.athleticspot.tracker.domain.model.SportActivityBuilder;
import com.athleticspot.tracker.domain.model.SportActivityType;
import com.athleticspot.tracker.domain.model.TrackerSource;
import com.google.common.collect.Lists;
import com.google.maps.model.LatLng;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest;
import org.springframework.test.context.junit4.SpringRunner;
import tech.units.indriya.unit.Units;

import javax.measure.MetricPrefix;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

/**
 * @author Tomasz Kasprzycki
 */
@RunWith(SpringRunner.class)
@DataNeo4jTest
public class SportActivityOutDtoAcceptanceTest {

    @Autowired
    private SportActivityRepository sportActivityRepository;

    @Autowired
    private GraphAthleteRepository graphAthleteRepository;

    private MeasureSystemProvider measureSystemProvider;

    @Before
    public void init() {
        measureSystemProvider = () -> MetricPrefix.KILO(Units.METRE);
    }

    @Test
    public void sportActivitySanityTest() {
        final String userUuid = UUID.randomUUID().toString();
        final TrackerSource trackerSource = TrackerSource.STRAVA;
        final SportActivityType sportActivityType = SportActivityType.RUN;
        final int trackingSystemId = 111;
        final String externalId = "9999";
        final float distance = 12F;
        final LocalDateTime startDate = LocalDateTime.now();
        final String firstAndLastName = "First and Last name";
        final String title = "title";
        final String description = "description";
        final int movingTime = 123;
        final int elapsedTime = 1000;
        final float totalElevationGain = 12.1F;
        final String timezone = "EST";
        final boolean privateActivity = false;
        final String gearId = "gearId";
        final float averageCadence = 123.1F;
        final float averageTemp = 1212F;
        final float averageWatts = 1.1F;
        final float kilojoules = 2.2F;
        final boolean deviceWatts = false;
        final boolean hasHeartrate = false;
        final float averageHeartrate = 0.1F;
        final int maxHeartrate = 12;
        final float calories = 123.34F;
        final float startLatitude = 12.12F;
        final float startLongitude = 12.11F;
        final String deviceName = "phone";
        final ArrayList<LatLng> coordinates = Lists.newArrayList(new LatLng(20.22, 12.11));

        SportActivity sportActivity = new SportActivityBuilder(
            trackerSource,
            sportActivityType,
            trackingSystemId,
            externalId,
            distance,
            startDate,
            title,
            userUuid,
            firstAndLastName
        ).setDescription(description)
            .setMovingTime(movingTime)
            .setElapsedTime(elapsedTime)
            .setTotalElevationGain(totalElevationGain)
            .setTimezone(timezone)
            .setPrivateActivity(privateActivity)
            .setGearId(gearId)
            .setAverageCadence(averageCadence)
            .setAverageTemp(averageTemp)
            .setAverageWatts(averageWatts)
            .setKilojoules(kilojoules)
            .setDeviceWatts(deviceWatts)
            .setHasHeartrate(hasHeartrate)
            .setAverageHeartrate(averageHeartrate)
            .setMaxHeartrate(maxHeartrate)
            .setCalories(calories)
            .setStartLatitude(startLatitude)
            .setStartLongitude(startLongitude)
            .setDeviceName(deviceName)
            .createSportActivity();

        sportActivityRepository.save(sportActivity);

        final Iterable<SportActivity> sportActivities = sportActivityRepository.findAll();
        Assertions.assertThat(sportActivities).hasSize(1);
        final SportActivity savedSportActivity = sportActivities.iterator().next();

        Assertions.assertThat(savedSportActivity.getTrackerSource()).isEqualTo(trackerSource);
        Assertions.assertThat(savedSportActivity.getSportActivityType()).isEqualTo(sportActivityType);
        Assertions.assertThat(savedSportActivity.getTrackingSystemId()).isEqualTo(trackingSystemId);
        Assertions.assertThat(savedSportActivity.getExternalId()).isEqualTo(externalId);
        Assertions.assertThat(savedSportActivity.getDistance(measureSystemProvider.getDistanceUnit())).isEqualTo(distance/1000);
        Assertions.assertThat(savedSportActivity.getStartDate()).isEqualTo(startDate);
        Assertions.assertThat(savedSportActivity.getTitle()).isEqualTo(title);
        Assertions.assertThat(savedSportActivity.getUserUuid()).isEqualTo(userUuid);
        Assertions.assertThat(savedSportActivity.getFirstAndLastName()).isEqualTo(firstAndLastName);

        Assertions.assertThat(savedSportActivity.getMovingTime()).isEqualTo(movingTime);
        Assertions.assertThat(savedSportActivity.getElapsedTime()).isEqualTo(elapsedTime);
        Assertions.assertThat(savedSportActivity.getTotalElevationGain()).isEqualTo(totalElevationGain);
        Assertions.assertThat(savedSportActivity.getTimezone()).isEqualTo(timezone);
        Assertions.assertThat(savedSportActivity.getPrivateActivity()).isEqualTo(privateActivity);
        Assertions.assertThat(savedSportActivity.getGearId()).isEqualTo(gearId);
        Assertions.assertThat(savedSportActivity.getAverageCadence()).isEqualTo(averageCadence);
        Assertions.assertThat(savedSportActivity.getAverageTemp()).isEqualTo(averageTemp);
        Assertions.assertThat(savedSportActivity.getKilojoules()).isEqualTo(kilojoules);
        Assertions.assertThat(savedSportActivity.getDeviceWatts()).isEqualTo(deviceWatts);
        Assertions.assertThat(savedSportActivity.getHasHeartrate()).isEqualTo(hasHeartrate);
        Assertions.assertThat(savedSportActivity.getAverageHeartrate()).isEqualTo(averageHeartrate);
        Assertions.assertThat(savedSportActivity.getMaxHeartrate()).isEqualTo(maxHeartrate);
        Assertions.assertThat(savedSportActivity.getCalories()).isEqualTo(calories);
        Assertions.assertThat(savedSportActivity.getStartLatitude()).isEqualTo(startLatitude);
        Assertions.assertThat(savedSportActivity.getStartLongitude()).isEqualTo(startLongitude);
        Assertions.assertThat(savedSportActivity.getDeviceName()).isEqualTo(deviceName);
    }

    @Test
    public void fetchingSportActivitiesAssignedToTheUser() {
        Athlete athlete = new Athlete("Olek", UUID.randomUUID().toString(), "");

        athlete.perform(new SportActivityBuilder(
            TrackerSource.STRAVA,
            SportActivityType.RUN,
            123,
            "externalId",
            12.1F,
            LocalDateTime.now(),
            "title",
            "123",
            "Tomasz Kasprzycki"
        ).createSportActivity());

        graphAthleteRepository.save(athlete);

        Assertions.assertThat(sportActivityRepository.findAll()).hasSize(1);
    }
}
