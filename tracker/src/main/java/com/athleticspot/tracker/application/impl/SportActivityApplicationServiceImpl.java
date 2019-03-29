package com.athleticspot.tracker.application.impl;

import com.athleticspot.common.SecurityUtils;
import com.athleticspot.tracker.application.SportActivityApplicationService;
import com.athleticspot.tracker.domain.graph.Athlete;
import com.athleticspot.tracker.domain.graph.GraphAthleteRepository;
import com.athleticspot.tracker.domain.graph.SportActivity;
import com.athleticspot.tracker.domain.graph.SportActivityRepository;
import com.athleticspot.tracker.domain.model.SportActivityBuilder;
import com.athleticspot.tracker.domain.model.TrackerSource;
import com.athleticspot.tracker.infrastracture.web.dto.in.SportActivityInDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

import static com.athleticspot.tracker.shared.QuantitiesConverter.convertDistanceToMeters;

/**
 * @author Tomasz Kasprzycki
 */
@Service
public class SportActivityApplicationServiceImpl implements SportActivityApplicationService {

    private static final int TRACKING_SYSTEM_ID = 0;
    private static final String EXTERNAL_ID = "0";

    private final GraphAthleteRepository graphAthleteRepository;

    private final SportActivityRepository sportActivityRepository;

    private DecimalFormat decimalFormat = new DecimalFormat("#0.000");

    @Autowired
    public SportActivityApplicationServiceImpl(GraphAthleteRepository graphAthleteRepository, SportActivityRepository sportActivityRepository) {
        this.graphAthleteRepository = graphAthleteRepository;
        this.sportActivityRepository = sportActivityRepository;
    }

    @Override
    public void createSportActivity(SportActivityInDto sportActivityInDto) {
        Athlete athlete = getCurrentAthlete();
        SportActivity sportActivity =
            new SportActivityBuilder(
                TrackerSource.values()[Integer.parseInt(sportActivityInDto.getTrackerSource())],
                sportActivityInDto.getSportActivityType(),
                TRACKING_SYSTEM_ID,
                EXTERNAL_ID,
                convertDistanceToMeters(sportActivityInDto.getUnits(), sportActivityInDto.getDistance()),
                sportActivityInDto.getStartDate(),
                sportActivityInDto.getTitle(),
                athlete.getAthleteUUID(),
                athlete.getFirstAndLastName()
            )
                .setElapsedTime(sportActivityInDto.getDuration())
                .setDescription(sportActivityInDto.getDescription())
                .setMaxSpeed(sportActivityInDto.getMaxSpeed())
                .setAverageSpeed(sportActivityInDto.getAverageSpeed())
                .createSportActivity();
        athlete.perform(sportActivity);
        graphAthleteRepository.save(athlete);
    }

    @Override
    public void deleteSportActivity(Long sportActivityId) {
        Athlete athlete = getCurrentAthlete();
        athlete.fetchActivity(sportActivityId)
            .orElseThrow(() -> new IllegalArgumentException(String.format("User with id: %s doesn't have sport activity with id: %s", athlete.getAthleteUUID(), sportActivityId)));
        sportActivityRepository.detachDeleteSportActivity(sportActivityId);

    }

    private Athlete getCurrentAthlete() {
        final String currentUserLogin = SecurityUtils.getCurrentUserLogin();
        return graphAthleteRepository.findByName(currentUserLogin)
            .orElseThrow(() -> new IllegalStateException(String.format("Athlete with name: %s doesn't exist", currentUserLogin)));
    }
}
