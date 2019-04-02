package com.athleticspot.tracker.infrastracture.web.dto.out;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * @author Tomasz Kasprzycki
 */
@JsonDeserialize
@JsonAutoDetect(
    fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class MeasureSystemOutDto {

    private String measureSystemType;

    public MeasureSystemOutDto(String measureSystemType) {
        this.measureSystemType = measureSystemType;
    }
}
