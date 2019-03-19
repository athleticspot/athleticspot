package com.athleticspot.tracker.application.impl;

import com.athleticspot.common.SecurityUtils;
import com.athleticspot.tracker.application.SportActivityApplicationService;
import com.athleticspot.tracker.domain.graph.*;
import com.athleticspot.tracker.domain.model.TrackerSource;
import com.athleticspot.tracker.infrastracture.web.dto.in.SportActivityInDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import systems.uom.common.USCustomary;
import tech.units.indriya.quantity.Quantities;

import java.text.DecimalFormat;

import static javax.measure.MetricPrefix.KILO;
import static tech.units.indriya.unit.Units.METRE;

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
                .setMaxSpeed(Float.parseFloat(decimalFormat.format(sportActivityInDto.getMaxSpeed())))
                .setAverageSpeed(Float.parseFloat(decimalFormat.format(sportActivityInDto.getAverageSpeed())))
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

    private Float convertDistanceToMeters(String units, Float distance){
        if("km".equals(units)){
            return (Float) Quantities.getQuantity(distance, KILO(METRE)).to(METRE).getValue();
        }else if("mil".equals(units)){
            return (Float) Quantities.getQuantity(distance, USCustomary.MILE).to(METRE).getValue();
        }
        return 0.00f;
    }
}
