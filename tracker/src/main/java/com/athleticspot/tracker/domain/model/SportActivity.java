package com.athleticspot.tracker.domain.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.maps.model.LatLng;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Tomasz Kasprzycki
 */
@JsonDeserialize
@JsonAutoDetect(
    fieldVisibility = JsonAutoDetect.Visibility.ANY,
    getterVisibility = JsonAutoDetect.Visibility.NONE,
    setterVisibility = JsonAutoDetect.Visibility.NONE)
public class SportActivity extends SportActivityGenericType {

    private String id;

    private String firstAndLastName;

    private String userUuid;

    private TrackerSource trackerSource;

    private SportActivityType sportActivityType;

    private String title;

    private String description;

    private Float distance;

    private String units;

    private String movingTime;

    private String elapsedTime;

    private LocalDateTime startDate;

    private Float averageSpeed;

    private Float maxSpeed;

    private Float averageTemp;

    private Float calories;

    private List<LatLng> coordinates;


    private SportActivity(String id, String username, String title, TrackerSource trackerSource, LocalDateTime startDate) {
        this.username = username;
        this.title = title;
        this.trackerSource = trackerSource;
        this.id = id;
        this.startDate = startDate;
    }

    public SportActivity() {

    }


    //Getters
    public String getId() {
        return id;
    }

    public String getFirstAndLastName() {
        return firstAndLastName;
    }

    public String getTitle() {
        return title;
    }

    public TrackerSource getTrackerSource() {
        return trackerSource;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }


    public SportActivityType getSportActivityType() {
        return sportActivityType;
    }

    public String getDescription() {
        return description;
    }

    public Float getDistance() {
        return distance;
    }

    public String getUnits() {
        return units;
    }

    public String getMovingTime() {
        return movingTime;
    }

    public String getElapsedTime() {
        return elapsedTime;
    }

    public Float getAverageSpeed() {
        return averageSpeed;
    }

    public Float getMaxSpeed() {
        return maxSpeed;
    }

    public Float getAverageTemp() {
        return averageTemp;
    }

    public Float getCalories() {
        return calories;
    }

    @Override
    public String getUsername() {
        return username;
    }

    //Fluent interfaces
    public SportActivity setId(final String id) {
        this.id = id;
        return this;
    }

    public SportActivity setUserUuid(final String userUuid) {
        this.userUuid = userUuid;
        return this;
    }


    public SportActivity setFirstAndLastName(final String firstAndLastName) {
        this.firstAndLastName = firstAndLastName;
        return this;
    }


    public SportActivity setTrackerSource(final TrackerSource trackerSource) {
        this.trackerSource = trackerSource;
        return this;
    }

    public SportActivity setSportActivityType(final SportActivityType sportActivityType) {
        this.sportActivityType = sportActivityType;
        return this;
    }

    public SportActivity setTitle(final String title) {
        this.title = title;
        return this;
    }

    public SportActivity setDescription(final String description) {
        this.description = description;
        return this;
    }

    public SportActivity setDistance(final Float distance) {
        this.distance = distance;
        return this;
    }

    public SportActivity setUnits(final String units) {
        this.units = units;
        return this;
    }


    public SportActivity setMovingTime(final String movingTime) {
        this.movingTime = movingTime;
        return this;
    }

    public SportActivity setElapsedTime(final String elapsedTime) {
        this.elapsedTime = elapsedTime;
        return this;
    }

    public SportActivity setStartDate(final LocalDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    public SportActivity setAverageSpeed(final Float averageSpeed) {
        this.averageSpeed = averageSpeed;
        return this;
    }

    public SportActivity setMaxSpeed(final Float maxSpeed) {
        this.maxSpeed = maxSpeed;
        return this;
    }

    public SportActivity setAverageTemp(final Float averageTemp) {
        this.averageTemp = averageTemp;
        return this;
    }

    public SportActivity setCalories(final Float calories) {
        this.calories = calories;
        return this;
    }

    public SportActivity setUsername(final String username) {
        this.username = username;
        return this;
    }

    public SportActivity setCoordinates(List<LatLng> coordinates) {
        this.coordinates = coordinates;
        return this;
    }
}
