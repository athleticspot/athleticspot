package com.athleticspot.tracker.domain.model;

import com.athleticspot.tracker.domain.shared.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Tomasz Kasprzycki
 */
@Embeddable
public class SportActivityDetails implements ValueObject {

    @Column(name = "description")
    private String description;

    @Column(name = "title")
    private String title;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private SportActivityType type;

    @Column(name = "duration")
    private String duration;

    @Column(name = "distance")
    private String distance;

    @Column(name = "units")
    private String units;

    @Column(name = "max_speed")
    private String maxSpeed;

    @Column(name = "mean_speed")
    private String meanSpeed;

    @Column(name = "activity_time")
    private LocalDateTime sportActivityTime;

    private SportActivityDetails() {
        //jpa purpose
    }

    private SportActivityDetails(String description, String title, SportActivityType type, String duration, String distance, String units, String maxSpeed, String meanSpeed, LocalDateTime sportActivityTime) {

        this.description = description;
        this.title = title;
        this.type = type;
        this.duration = duration;
        this.distance = distance;
        this.units = units;
        this.maxSpeed = maxSpeed;
        this.meanSpeed = meanSpeed;
        this.sportActivityTime = sportActivityTime;
    }

    public static SportActivityDetails create(
        String sportActivityDescription,
        String sportActivityTitle,
        SportActivityType sportActivityType,
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

    public SportActivityType type() {
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
        return sportActivityTime;
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
            Objects.equals(sportActivityTime, that.sportActivityTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, title, type, duration, distance, units, maxSpeed, meanSpeed, sportActivityTime);
    }
}
