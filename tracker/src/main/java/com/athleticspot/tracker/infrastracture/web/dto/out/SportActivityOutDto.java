package com.athleticspot.tracker.infrastracture.web.dto.out;

import com.athleticspot.tracker.domain.model.SportActivityType;
import com.athleticspot.tracker.domain.model.TrackerSource;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.maps.model.LatLng;
import org.springframework.util.Assert;
import tech.units.indriya.unit.Units;

import java.text.DecimalFormat;
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
public class SportActivityOutDto {

    private String id;

    private String firstAndLastName;

    protected String username;

    private String userUuid;

    private TrackerSource trackerSource;

    private SportActivityType sportActivityType;

    private String title;

    private String description;

    private Float distance;

    private String units;

    private String movingTime;

    //Formated representation for UI: "hh mm ss"
    private String elapsedTime;

    private Integer elapsedTimeInSeconds;

    private LocalDateTime startDate;

    private Float averageSpeed;

    private Float maxSpeed;

    private Float averageTemp;

    private Float calories;

    private List<LatLng> coordinates;

    private Float pace;

    private String formattedPace;


    public SportActivityOutDto() {
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

    public String getUsername() {
        return username;
    }

    public Float getPace() {
        return pace;
    }

    public String getFormattedPace() {
        return formattedPace;
    }

    //Fluent interfaces
    public SportActivityOutDto setId(final String id) {
        this.id = id;
        return this;
    }

    public SportActivityOutDto setUserUuid(final String userUuid) {
        this.userUuid = userUuid;
        return this;
    }


    public SportActivityOutDto setFirstAndLastName(final String firstAndLastName) {
        this.firstAndLastName = firstAndLastName;
        return this;
    }


    public SportActivityOutDto setTrackerSource(final TrackerSource trackerSource) {
        this.trackerSource = trackerSource;
        return this;
    }

    public SportActivityOutDto setSportActivityType(final SportActivityType sportActivityType) {
        this.sportActivityType = sportActivityType;
        return this;
    }

    public SportActivityOutDto setTitle(final String title) {
        this.title = title;
        return this;
    }

    public SportActivityOutDto setDescription(final String description) {
        this.description = description;
        return this;
    }

    public SportActivityOutDto setDistance(final Float distance) {
        Assert.notNull(distance, "distance cannot be null");
        this.distance = distance;
        return this;
    }

    public SportActivityOutDto setDistanceUnits(final String units) {
        this.units = units;
        return this;
    }


    public SportActivityOutDto setMovingTime(final String movingTime) {
        this.movingTime = movingTime;
        return this;
    }

    public SportActivityOutDto setElapsedTime(final String elapsedTime) {
        Assert.notNull(elapsedTime, "elapsed time cannot be null");
        this.elapsedTime = elapsedTime;
        return this;
    }

    public SportActivityOutDto setElapsedTimeInSeconds(Integer elapsedTimeInSeconds) {
        this.elapsedTimeInSeconds = elapsedTimeInSeconds;
        return this;
    }

    public SportActivityOutDto setStartDate(final LocalDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    public SportActivityOutDto setAverageSpeed(final Float averageSpeed) {
        this.averageSpeed = averageSpeed;
        return this;
    }

    public SportActivityOutDto setMaxSpeed(final Float maxSpeed) {
        this.maxSpeed = maxSpeed;
        return this;
    }

    public SportActivityOutDto setAverageTemp(final Float averageTemp) {
        this.averageTemp = averageTemp;
        return this;
    }

    public SportActivityOutDto setCalories(final Float calories) {
        this.calories = calories;
        return this;
    }

    public SportActivityOutDto setUsername(final String username) {
        this.username = username;
        return this;
    }

    public SportActivityOutDto setCoordinates(List<LatLng> coordinates) {
        this.coordinates = coordinates;
        return this;
    }

    /**
     * pace is calculated in miles or kilometers per minute
     *
     * @return
     */
    public SportActivityOutDto setPace() {
        float calculatedPace;
        if (Units.METRE.toString().equals(this.units)) {
            calculatedPace = (this.elapsedTimeInSeconds) / (this.distance / 1000) / 60;
        } else {
            calculatedPace = (this.elapsedTimeInSeconds) / this.distance / 60;
        }
        this.pace = Float.parseFloat(new DecimalFormat("#0.00").format(calculatedPace));
        calculateFormattedPace();
        return this;
    }

    private void calculateFormattedPace() {
        Assert.notNull(pace, "Pace cannot be null in order to format it");
        final float paceDecimalPart = this.pace % 1;

        this.formattedPace = new DecimalFormat("#0").format(Math.floor(this.pace))
            + ":"
            + new DecimalFormat("#0").format(paceDecimalPart * 60);
    }


}
