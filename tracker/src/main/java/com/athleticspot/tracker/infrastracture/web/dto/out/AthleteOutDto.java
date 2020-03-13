package com.athleticspot.tracker.infrastracture.web.dto.out;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;

/**
 * @author Tomasz Kasprzycki
 */
@JsonDeserialize
@JsonAutoDetect(
    fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class AthleteOutDto implements Serializable {

    private final String name;
    private final String athleteUUID;
    private final Long id;
    private final String firstAndLastName;
    //Indicates if current logged in user follows user represented by this DTO follows .
    private final boolean followedByCurrent;
    private final String imageUrl;

    public AthleteOutDto(String name,
                         String athleteUUID,
                         Long id,
                         String firstAndLastName,
                         boolean followedByCurrent,
                         String imageUrl) {
        this.name = name;
        this.athleteUUID = athleteUUID;
        this.id = id;
        this.firstAndLastName = firstAndLastName;
        this.followedByCurrent = followedByCurrent;
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "AthleteOutDto{" +
            "name='" + name + '\'' +
            ", athleteUUID='" + athleteUUID + '\'' +
            ", id=" + id +
            ", firstAndLastName='" + firstAndLastName + '\'' +
            ", followedByCurrent=" + followedByCurrent +
            '}';
    }
}
