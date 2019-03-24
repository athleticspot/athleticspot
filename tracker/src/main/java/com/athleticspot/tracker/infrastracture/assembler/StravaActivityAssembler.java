package com.athleticspot.tracker.infrastracture.assembler;

import com.athleticspot.tracker.domain.model.SportActivity;
import com.athleticspot.tracker.domain.model.SportActivityType;
import com.athleticspot.tracker.domain.model.UserRepository;
import com.athleticspot.tracker.domain.model.manual.ManualSportActivity;
import com.athleticspot.tracker.domain.model.manual.ManualSportActivityDetails;
import com.athleticspot.tracker.domain.model.strava.StravaSportActivity;
import com.google.maps.internal.PolylineEncoding;
import javastrava.api.v3.model.StravaActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Tomasz Kasprzycki
 */
@Component
public class StravaActivityAssembler implements SportActivityAssembler<StravaSportActivity>{

    private final UserRepository userRepository;

    @Autowired
    public StravaActivityAssembler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ManualSportActivity buildFromStrava(StravaActivity stravaActivity) {

        final ManualSportActivityDetails manualSportActivityDetails = ManualSportActivityDetails.create(
            stravaActivity.getDescription(),
            stravaActivity.getName(),
            SportActivityType.valueOf(stravaActivity.getType().getValue().toUpperCase()),
            stravaActivity.getElapsedTime(),
            stravaActivity.getDistance().toString(),
            null,
            stravaActivity.getMaxSpeed().toString(),
            stravaActivity.getAverageSpeed().toString()
        );

        return ManualSportActivity.createNew(
            "STRAVA",
            manualSportActivityDetails,
            stravaActivity.getStartDateLocal()
        );
    }

    public List<ManualSportActivity> buildFromStravaActivities(List<StravaActivity> stravaActivities) {
        return stravaActivities.stream()
            .map(this::buildFromStrava)
            .collect(Collectors.toList());
    }

    @Override
    public SportActivity assembleSportActivity(StravaSportActivity stravaSportActivity) {
        return new SportActivity()
            .setId(stravaSportActivity.getId())
            .setUsername(stravaSportActivity.getUsername())
            .setTrackerSource(stravaSportActivity.getTrackerSource())
            .setSportActivityType(stravaSportActivity.getSportActivityType())
            .setTitle(stravaSportActivity.getTitle())
            .setDescription(stravaSportActivity.getDescription())
            .setDistance(stravaSportActivity.getDistance())
            .setUnits("m") //be default strava activities are stored as a meters
//            .setMovingTime(stravaSportActivity.getMovingTime())
//            .setElapsedTime(stravaSportActivity.getElapsedTime())
            .setStartDate(stravaSportActivity.getStartDate())
            .setAverageSpeed(stravaSportActivity.getAverageSpeed())
            .setMaxSpeed(stravaSportActivity.getMaxSpeed())
            .setAverageTemp(stravaSportActivity.getAverageTemp())
            .setCalories(stravaSportActivity.getCalories())
            .setCoordinates(PolylineEncoding.decode(
                Optional.ofNullable(stravaSportActivity.getMap().getSummaryPolyline()).orElseGet(() -> ""))
            );
    }
}
