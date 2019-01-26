package com.athleticspot.tracker.infrastracture.web.dto.in;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * @author Tomasz Kasprzycki
 */
@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class FallowInDto {

    private String athleteIdToFallow;

    public String getAthleteIdToFallow() {
        return athleteIdToFallow;
    }

    @Override
    public String toString() {
        return "FallowInDto{" +
                ", athleteIdToFallow='" + athleteIdToFallow + '\'' +
                '}';
    }
}
