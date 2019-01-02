package com.athleticspot.tracker.infrastracture.web.dto.in;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * @author Tomasz Kasprzycki
 */
@JsonDeserialize
@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        setterVisibility = JsonAutoDetect.Visibility.NONE)
public class AthleteInDto {

    private String name;

    public String getName() {
        return this.name;
    }
}