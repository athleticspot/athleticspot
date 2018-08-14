package com.athleticspot.tracker.infrastracture.web.dto.out;

import com.athleticspot.tracker.domain.model.SportActivityDetails;

import java.time.LocalDateTime;

/**
 * @author Tomasz Kasprzycki
 */
public class SportActivityDetailsOutDto {

    private String description;
    private String title;
    private String type;
    private String duration;
    private String distance;
    private String units;
    private String maxSpeed;
    private String meanSpeed;
    private LocalDateTime dateTime;

    private SportActivityDetailsOutDto(SportActivityDetails sportActivityDetails) {
        this.description = sportActivityDetails.description();
        this.title = sportActivityDetails.title();
        this.type = sportActivityDetails.type();
        this.duration = sportActivityDetails.duration();
        this.distance = sportActivityDetails.distance();
        this.units = sportActivityDetails.units();
        this.maxSpeed = sportActivityDetails.maxSpeed();
        this.meanSpeed = sportActivityDetails.meanSpeed();
        this.dateTime = sportActivityDetails.dateTime();
    }

    public static SportActivityDetailsOutDto create(SportActivityDetails sportActivityDetails) {
        return new SportActivityDetailsOutDto(sportActivityDetails);
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
        return "SportActivityDetailsOutDto{" +
            "description='" + description + '\'' +
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
