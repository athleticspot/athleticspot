package com.athleticspot.tracker.infrastracture.web.dto.in;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * @author Tomasz Kasprzycki
 */
@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class FallowInDto {

    private String athleteId;
    private String athleteIdToFallow;

    public String getAthleteId() {
        return athleteId;
    }

    public String getAthleteIdToFallow() {
        return athleteIdToFallow;
    }

    @Override
    public String toString() {
        return "FallowInDto{" +
                "athleteId='" + athleteId + '\'' +
                ", athleteIdToFallow='" + athleteIdToFallow + '\'' +
                '}';
    }
}
