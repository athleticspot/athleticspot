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
import java.util.Objects;

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

    private String externalId;

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

    private String activityUrl;

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

    public String getActivityUrl() {
        return activityUrl;
    }

    //Fluent interfaces
    public SportActivityOutDto setId(final String id) {
        this.id = id;
        return this;
    }

    public SportActivityOutDto setExternalId(final String externalId) {
        this.externalId = externalId;
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

    public SportActivityOutDto setActivityUrl(String activityUrl) {
        this.activityUrl = activityUrl;
        return this;
    }

    /**
     * pace is calculated in miles or kilometers per minute
     *
     * @return
     */
    public SportActivityOutDto setPace() {
        if(0f == this.distance){
            this.pace = 0f;
            this.formattedPace = "00:00";
            return this;
        }
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

        this.formattedPace = new DecimalFormat("#00").format(Math.floor(this.pace))
            + ":"
            + new DecimalFormat("#00").format(paceDecimalPart * 60);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SportActivityOutDto)) return false;
        SportActivityOutDto that = (SportActivityOutDto) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(externalId, that.externalId) &&
            Objects.equals(firstAndLastName, that.firstAndLastName) &&
            Objects.equals(username, that.username) &&
            Objects.equals(userUuid, that.userUuid) &&
            trackerSource == that.trackerSource &&
            sportActivityType == that.sportActivityType &&
            Objects.equals(title, that.title) &&
            Objects.equals(description, that.description) &&
            Objects.equals(distance, that.distance) &&
            Objects.equals(units, that.units) &&
            Objects.equals(movingTime, that.movingTime) &&
            Objects.equals(elapsedTime, that.elapsedTime) &&
            Objects.equals(elapsedTimeInSeconds, that.elapsedTimeInSeconds) &&
            Objects.equals(startDate, that.startDate) &&
            Objects.equals(averageSpeed, that.averageSpeed) &&
            Objects.equals(maxSpeed, that.maxSpeed) &&
            Objects.equals(averageTemp, that.averageTemp) &&
            Objects.equals(calories, that.calories) &&
            Objects.equals(coordinates, that.coordinates) &&
            Objects.equals(pace, that.pace) &&
            Objects.equals(formattedPace, that.formattedPace) &&
            Objects.equals(activityUrl, that.activityUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, externalId, firstAndLastName, username, userUuid, trackerSource, sportActivityType, title, description, distance, units, movingTime, elapsedTime, elapsedTimeInSeconds, startDate, averageSpeed, maxSpeed, averageTemp, calories, coordinates, pace, formattedPace, activityUrl);
    }

    @Override
    public String toString() {
        return "SportActivityOutDto{" +
            "id='" + id + '\'' +
            ", externalId='" + externalId + '\'' +
            ", firstAndLastName='" + firstAndLastName + '\'' +
            ", username='" + username + '\'' +
            ", userUuid='" + userUuid + '\'' +
            ", trackerSource=" + trackerSource +
            ", sportActivityType=" + sportActivityType +
            ", title='" + title + '\'' +
            ", description='" + description + '\'' +
            ", distance=" + distance +
            ", units='" + units + '\'' +
            ", movingTime='" + movingTime + '\'' +
            ", elapsedTime='" + elapsedTime + '\'' +
            ", elapsedTimeInSeconds=" + elapsedTimeInSeconds +
            ", startDate=" + startDate +
            ", averageSpeed=" + averageSpeed +
            ", maxSpeed=" + maxSpeed +
            ", averageTemp=" + averageTemp +
            ", calories=" + calories +
            ", coordinates=" + coordinates +
            ", pace=" + pace +
            ", formattedPace='" + formattedPace + '\'' +
            '}';
    }
}
