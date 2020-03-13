package com.athleticspot.tracker.infrastracture.assembler;

import com.athleticspot.tracker.domain.graph.Athlete;
import com.athleticspot.tracker.infrastracture.web.dto.out.AthleteOutDto;

/**
 * @author Tomasz Kasprzycki
 */
public interface AthleteAssembler {

    AthleteOutDto assembleAthlete(Athlete athlete);
}
