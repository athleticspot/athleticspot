package com.athleticspot.tracker.domain.model;

import com.athleticspot.tracker.domain.shared.ValueObject;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Tomasz Kasprzycki
 */
@Embeddable
public class SportActivityDetails implements ValueObject {

    private String description;
    private String title;
    private String type;
    private String duration;
    private String distance;
    private String units;
    private String maxSpeed;
    private String meanSpeed;
    private LocalDateTime dateTime;

    private SportActivityDetails() {
        //jpa purpose
    }

    private SportActivityDetails(String description, String title, String type, String duration, String distance, String units, String maxSpeed, String meanSpeed, LocalDateTime dateTime) {

        this.description = description;
        this.title = title;
        this.type = type;
        this.duration = duration;
        this.distance = distance;
        this.units = units;
        this.maxSpeed = maxSpeed;
        this.meanSpeed = meanSpeed;
        this.dateTime = dateTime;
    }

    public static SportActivityDetails create(
        String sportActivityDescription,
        String sportActivityTitle,
        String sportActivityType,
        String sportActivityDuration,
        String sportActivityDistance,
        String sportActivityUnits,
        String sportActivityMaxSpeed,
        String sportActivityMeanSpeed,
        LocalDateTime sportActivityDateTime) {
        return new SportActivityDetails(sportActivityDescription, sportActivityTitle, sportActivityType, sportActivityDuration, sportActivityDistance, sportActivityUnits, sportActivityMaxSpeed, sportActivityMeanSpeed, sportActivityDateTime);
    }

    public String description() {
        return description;
    }

    public String title() {
        return title;
    }

    public String type() {
        return type;
    }

    public String duration() {
        return duration;
    }

    public String distance() {
        return distance;
    }

    public String units() {
        return units;
    }

    public String maxSpeed() {
        return maxSpeed;
    }

    public String meanSpeed() {
        return meanSpeed;
    }

    public LocalDateTime dateTime() {
        return dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SportActivityDetails)) return false;
        SportActivityDetails that = (SportActivityDetails) o;
        return Objects.equals(description, that.description) &&
            Objects.equals(title, that.title) &&
            Objects.equals(type, that.type) &&
            Objects.equals(duration, that.duration) &&
            Objects.equals(distance, that.distance) &&
            Objects.equals(units, that.units) &&
            Objects.equals(maxSpeed, that.maxSpeed) &&
            Objects.equals(meanSpeed, that.meanSpeed) &&
            Objects.equals(dateTime, that.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, title, type, duration, distance, units, maxSpeed, meanSpeed, dateTime);
    }
}
