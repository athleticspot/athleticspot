package com.athleticspot.tracker.domain.model;

import com.athleticspot.tracker.domain.shared.ValueObject;

import java.time.LocalDateTime;

/**
 * @author Tomasz Kasprzycki
 */
public class SportActivityDetails implements ValueObject {

    private final String description;
    private final String title;
    private final String type;
    private final String duration;
    private final String distance;
    private final String units;
    private final String maxSpeed;
    private final String meanSpeed;
    private final LocalDateTime dateTime;

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
}
