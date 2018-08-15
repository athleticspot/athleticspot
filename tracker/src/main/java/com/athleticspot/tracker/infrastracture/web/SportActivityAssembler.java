package com.athleticspot.tracker.infrastracture.web;

import com.athleticspot.tracker.domain.model.SportActivity;
import com.athleticspot.tracker.domain.model.SportActivityDetails;
import com.athleticspot.tracker.infrastracture.web.dto.in.SportActivityInDto;

/**
 * @author Tomasz Kasprzycki
 */
class SportActivityAssembler {

    static SportActivityDetails assemble(SportActivityInDto sportActivityInDto) {
        return SportActivityDetails.create(
            sportActivityInDto.getDescription(),
            sportActivityInDto.getTitle(),
            sportActivityInDto.getType(),
            sportActivityInDto.getDuration(),
            sportActivityInDto.getDistance(),
            sportActivityInDto.getUnits(),
            sportActivityInDto.getMaxSpeed(),
            sportActivityInDto.getMeanSpeed(),
            sportActivityInDto.getDateTime()
        );
    }
}
