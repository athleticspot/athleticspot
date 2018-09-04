package com.athleticspot.tracker.infrastracture.web.dto.in;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Tomasz Kasprzycki
 */

@JsonDeserialize
@JsonAutoDetect(
    fieldVisibility = JsonAutoDetect.Visibility.ANY,
    getterVisibility = JsonAutoDetect.Visibility.NONE,
    setterVisibility = JsonAutoDetect.Visibility.NONE)
public class SportActivityInDto implements Serializable {

    private String sportyActivityIdentifier;

    @NotNull
    private String source;

    private String description;

    @NotNull
    private String title;

    @NotNull
    private String type;

    @NotNull
    private String duration;

    @NotNull
    private String distance;

    private String units;

    private String maxSpeed;

    private String meanSpeed;

    @NotNull
    private LocalDateTime dateTime;

    public String getSportyActivityIdentifier() {
        return sportyActivityIdentifier;
    }

    public String getSource() {
        return source;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getDuration() {
        return duration;
    }

    public String getDistance() {
        return distance;
    }

    public String getUnits() {
        return units;
    }

    public String getMaxSpeed() {
        return maxSpeed;
    }

    public String getMeanSpeed() {
        return meanSpeed;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "SportActivityInDto{" +
            "sportyActivityIdentifier='" + sportyActivityIdentifier + '\'' +
            ", source='" + source + '\'' +
            ", description='" + description + '\'' +
            ", title='" + title + '\'' +
            ", type='" + type + '\'' +
            ", duration='" + duration + '\'' +
            ", distance='" + distance + '\'' +
            ", units='" + units + '\'' +
            ", maxSpeed='" + maxSpeed + '\'' +
            ", meanSpeed='" + meanSpeed + '\'' +
            ", dateTime=" + dateTime +
            '}';
    }
}
