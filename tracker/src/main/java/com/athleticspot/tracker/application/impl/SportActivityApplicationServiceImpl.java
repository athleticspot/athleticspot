package com.athleticspot.tracker.application.impl;

import com.athleticspot.common.SecurityUtils;
import com.athleticspot.tracker.application.SportActivityApplicationService;
import com.athleticspot.tracker.domain.graph.*;
import com.athleticspot.tracker.domain.model.TrackerSource;
import com.athleticspot.tracker.infrastracture.web.dto.in.SportActivityInDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tomasz Kasprzycki
 */
@Service
public class SportActivityApplicationServiceImpl implements SportActivityApplicationService {

    private final GraphAthleteRepository graphAthleteRepository;

    private final SportActivityRepository sportActivityRepository;

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
                0,
                "0",
                Float.parseFloat(sportActivityInDto.getDistance()),
                sportActivityInDto.getStartDate(),
                sportActivityInDto.getTitle(),
                athlete.getAthleteUUID(),
                athlete.getFirstAndLastName()
            ).createSportActivity();
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
