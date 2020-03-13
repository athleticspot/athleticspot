package com.athleticspot.tracker.infrastracture.web.dto.in;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * @author Tomasz Kasprzycki
 */
@JsonDeserialize
@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class AthleteInDto {

    private String name;

    private String athleteUuid;

    private String firstAndLastName;

    public String getName() {
        return this.name;
    }

    public String getAthleteUuid() {
        return athleteUuid;
    }

    public String getFirstAndLastName() {
        return firstAndLastName;
    }
}
