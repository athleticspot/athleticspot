package com.athleticspot.tracker.infrastracture.web.assembler;

import com.athleticspot.tracker.domain.model.manual.ManualSportActivityDetails;
import com.athleticspot.tracker.infrastracture.web.dto.in.SportActivityInDto;

/**
 * @author Tomasz Kasprzycki
 */
public class ManualSportActivityDetailsInDtoAssembler {

    public static ManualSportActivityDetails assemble(SportActivityInDto sportActivityInDto) {
        return ManualSportActivityDetails.create(
            sportActivityInDto.getDescription(),
            sportActivityInDto.getTitle(),
            sportActivityInDto.getType(),
            sportActivityInDto.getDuration(),
            sportActivityInDto.getDistance(),
            sportActivityInDto.getUnits(),
            sportActivityInDto.getMaxSpeed(),
            sportActivityInDto.getMeanSpeed()
        );
    }

}
