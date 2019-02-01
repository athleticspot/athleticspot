package com.athleticspot.tracker.domain.graph;

import com.athleticspot.tracker.domain.model.SportActivityType;
import com.athleticspot.tracker.domain.model.TrackerSource;
import com.google.maps.model.LatLng;
import javastrava.api.v3.model.StravaActivity;

import java.time.LocalDateTime;
import java.util.List;

public class SportActivityBuilder {

    private TrackerSource trackerSource;
    private SportActivityType sportActivityType;
    private Integer trackingSystemId;
    private String externalId;
    private Float distance;
    private LocalDateTime startDate;
    private String userUuid;
    private String firstAndLastName;
    private String title;
    private String description;
    private Integer movingTime;
    private Integer elapsedTime;
    private Float totalElevationGain;
    private String timezone;
    private Boolean privateActivity;
    private String gearId;
    private Float averageSpeed;
    private Float maxSpeed;
    private Float averageCadence;
    private Float averageTemp;
    private Float averageWatts;
    private Float weightedAverageWatts;
    private Float kilojoules;
    private Boolean deviceWatts;
    private Boolean hasHeartrate;
    private Float averageHeartrate;
    private Integer maxHeartrate;
    private Float calories;
    private Float startLatitude;
    private Float startLongitude;
    private String deviceName;
    private List<LatLng> coordinates;
    private String originalActivity;

    public SportActivityBuilder(TrackerSource trackerSource,
                                SportActivityType sportActivityType,
                                Integer trackingSystemId,
                                String externalId,
                                Float distance,
                                LocalDateTime startDate,
                                String title,
                                String userUuid,
                                String firstAndLastName) {
        this.trackerSource = trackerSource;
        this.sportActivityType = sportActivityType;
        this.trackingSystemId = trackingSystemId;
        this.externalId = externalId;
        this.distance = distance;
        this.startDate = startDate;
        this.title = title;
        this.userUuid = userUuid;
        this.firstAndLastName = firstAndLastName;
    }

    public static SportActivityBuilder createFromStravaActivity(StravaActivity stravaActivity,
                                                                String userUuid,
                                                                String firstAndLastName) {
        return new SportActivityBuilder(
            TrackerSource.STRAVA,
            SportActivityType.valueOf(stravaActivity.getType().name()),
            1,
            stravaActivity.getId().toString(),
            stravaActivity.getDistance(),
            stravaActivity.getStartDateLocal(),
            stravaActivity.getName(),
            userUuid,
            firstAndLastName

        );
    }


    public SportActivityBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public SportActivityBuilder setMovingTime(Integer movingTime) {
        this.movingTime = movingTime;
        return this;
    }

    public SportActivityBuilder setElapsedTime(Integer elapsedTime) {
        this.elapsedTime = elapsedTime;
        return this;
    }

    public SportActivityBuilder setTotalElevationGain(Float totalElevationGain) {
        this.totalElevationGain = totalElevationGain;
        return this;
    }

    public SportActivityBuilder setTimezone(String timezone) {
        this.timezone = timezone;
        return this;
    }

    public SportActivityBuilder setPrivateActivity(Boolean privateActivity) {
        this.privateActivity = privateActivity;
        return this;
    }

    public SportActivityBuilder setGearId(String gearId) {
        this.gearId = gearId;
        return this;
    }

    public SportActivityBuilder setAverageSpeed(Float averageSpeed) {
        this.averageSpeed = averageSpeed;
        return this;
    }

    public SportActivityBuilder setMaxSpeed(Float maxSpeed) {
        this.maxSpeed = maxSpeed;
        return this;
    }

    public SportActivityBuilder setAverageCadence(Float averageCadence) {
        this.averageCadence = averageCadence;
        return this;
    }

    public SportActivityBuilder setAverageTemp(Float averageTemp) {
        this.averageTemp = averageTemp;
        return this;
    }

    public SportActivityBuilder setAverageWatts(Float averageWatts) {
        this.averageWatts = averageWatts;
        return this;
    }

    public SportActivityBuilder setWeightedAverageWatts(Float weightedAverageWatts) {
        this.weightedAverageWatts = weightedAverageWatts;
        return this;
    }

    public SportActivityBuilder setKilojoules(Float kilojoules) {
        this.kilojoules = kilojoules;
        return this;
    }

    public SportActivityBuilder setDeviceWatts(Boolean deviceWatts) {
        this.deviceWatts = deviceWatts;
        return this;
    }

    public SportActivityBuilder setHasHeartrate(Boolean hasHeartrate) {
        this.hasHeartrate = hasHeartrate;
        return this;
    }

    public SportActivityBuilder setAverageHeartrate(Float averageHeartrate) {
        this.averageHeartrate = averageHeartrate;
        return this;
    }

    public SportActivityBuilder setMaxHeartrate(Integer maxHeartrate) {
        this.maxHeartrate = maxHeartrate;
        return this;
    }

    public SportActivityBuilder setCalories(Float calories) {
        this.calories = calories;
        return this;
    }

    public SportActivityBuilder setStartLatitude(Float startLatitude) {
        this.startLatitude = startLatitude;
        return this;
    }

    public SportActivityBuilder setStartLongitude(Float startLongitude) {
        this.startLongitude = startLongitude;
        return this;
    }

    public SportActivityBuilder setDeviceName(String deviceName) {
        this.deviceName = deviceName;
        return this;
    }

    public SportActivityBuilder setCoordinates(List<LatLng> coordinates) {
        this.coordinates = coordinates;
        return this;
    }

    public SportActivityBuilder setOriginalActivity(final String originalActivity) {
        this.originalActivity = originalActivity;
        return this;
    }


    public SportActivity createSportActivity() {
        return new SportActivity(
            userUuid,
            firstAndLastName,
            trackerSource,
            sportActivityType,
            trackingSystemId,
            externalId,
            title,
            description,
            distance,
            movingTime,
            elapsedTime,
            totalElevationGain,
            startDate,
            timezone,
            privateActivity,
            gearId,
            averageSpeed,
            maxSpeed,
            averageCadence,
            averageTemp,
            averageWatts,
            weightedAverageWatts,
            kilojoules,
            deviceWatts,
            hasHeartrate,
            averageHeartrate,
            maxHeartrate,
            calories,
            startLatitude,
            startLongitude,
            deviceName,
            coordinates,
            originalActivity
        );
    }
}
