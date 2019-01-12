package com.athleticspot.tracker.infrastracture.assembler;

import com.athleticspot.tracker.domain.graph.Athlete;
import com.athleticspot.tracker.infrastracture.web.dto.out.AthleteOutDto;
import org.springframework.stereotype.Service;

/**
 * @author Tomasz Kasprzycki
 */
@Service
public class AthleteAssemblerImpl implements AthleteAssembler {

    @Override
    public AthleteOutDto assembleAthlete(Athlete athlete) {
        return new AthleteOutDto(athlete.getName(),
            athlete.getAthleteUUID(),
            athlete.getId());

    }
}
