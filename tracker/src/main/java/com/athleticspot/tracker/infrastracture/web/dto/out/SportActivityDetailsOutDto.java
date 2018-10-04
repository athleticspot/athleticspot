package com.athleticspot.tracker.infrastracture.web.dto.out;

import com.athleticspot.tracker.domain.model.SportActivityType;
import com.athleticspot.tracker.domain.model.manual.ManualSportActivityDetails;

import java.time.LocalDateTime;

/**
 * @author Tomasz Kasprzycki
 */
public class SportActivityDetailsOutDto {

    private String description;
    private String title;
    private SportActivityType type;
    private String duration;
    private String distance;
    private String units;
    private String maxSpeed;
    private String meanSpeed;
    private LocalDateTime dateTime;

    private SportActivityDetailsOutDto(ManualSportActivityDetails manualSportActivityDetails) {
        this.description = manualSportActivityDetails.description();
        this.title = manualSportActivityDetails.title();
        this.type = manualSportActivityDetails.type();
        this.duration = manualSportActivityDetails.duration();
        this.distance = manualSportActivityDetails.distance();
        this.units = manualSportActivityDetails.units();
        this.maxSpeed = manualSportActivityDetails.maxSpeed();
        this.meanSpeed = manualSportActivityDetails.meanSpeed();
        this.dateTime = manualSportActivityDetails.dateTime();
    }

    public static SportActivityDetailsOutDto create(ManualSportActivityDetails manualSportActivityDetails) {
        return new SportActivityDetailsOutDto(manualSportActivityDetails);
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public SportActivityType getType() {
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
