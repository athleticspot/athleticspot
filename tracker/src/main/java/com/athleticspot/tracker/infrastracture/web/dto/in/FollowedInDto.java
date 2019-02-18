package com.athleticspot.tracker.infrastracture.web.dto.in;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

/**
 * @author Tomasz Kasprzycki
 */
@JsonDeserialize
@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class FollowedInDto {

    private List<Long> athleteIds;

    public List<Long> getAthleteIds() {
        return athleteIds;
    }

    @Override
    public String toString() {
        return "FollowAthleteInDto{" +
                ", athleteIds='" + athleteIds + '\'' +
                '}';
    }
}
