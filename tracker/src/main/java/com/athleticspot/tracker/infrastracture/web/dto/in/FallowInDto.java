package com.athleticspot.tracker.infrastracture.web.dto.in;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * @author Tomasz Kasprzycki
 */
@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class FallowInDto {

    private Long athleteId;

    public Long getAthleteId() {
        return athleteId;
    }

    @Override
    public String toString() {
        return "FallowInDto{" +
                ", athleteId='" + athleteId + '\'' +
                '}';
    }
}
