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

    private final SportActivityRepository sportActivityRepository;

    private final GraphAthleteRepository graphAthleteRepository;

    @Autowired
    public SportActivityApplicationServiceImpl(SportActivityRepository sportActivityRepository,
                                               GraphAthleteRepository graphAthleteRepository) {
        this.sportActivityRepository = sportActivityRepository;
        this.graphAthleteRepository = graphAthleteRepository;
    }

    @Override
    public void createSportActivity(SportActivityInDto sportActivityInDto) {
        final String currentUserLogin = SecurityUtils.getCurrentUserLogin();
        final Athlete athlete =
            graphAthleteRepository.findByName(currentUserLogin)
                .orElseThrow(() -> new IllegalStateException(String.format("Athlete with name: %s doesn't exist", currentUserLogin)));
        SportActivity sportActivity =
            new SportActivityBuilder(
                TrackerSource.valueOf(sportActivityInDto.getTrackerSource()),
                sportActivityInDto.getSportActivityType(),
                123,
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
}
