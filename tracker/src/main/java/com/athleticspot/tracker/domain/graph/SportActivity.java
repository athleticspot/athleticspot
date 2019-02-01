package com.athleticspot.tracker.domain.graph;

import com.athleticspot.tracker.domain.model.SportActivityType;
import com.athleticspot.tracker.domain.model.TrackerSource;
import com.google.maps.model.LatLng;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Tomasz Kasprzycki
 */
@NodeEntity
public class SportActivity {

    @Id
    @GeneratedValue
    private Long id;

    private String userUuid;

    private String firstAndLastName;

    /**
     * Manual, Strava, etc
     */
    private TrackerSource trackerSource;

    /**
     * Ride, Run, etc ...
     */
    private SportActivityType sportActivityType;

    private Integer trackingSystemId;

    /**
     * The identifier given to the activity by the application that uploaded it
     */
    private String externalId;

    private String title;

    private String description;

    /**
     * Distance travelled in metres. If you want it in funny old imperial,
     * that's up to you to convert it
     */
    private Float distance;

    private Integer movingTime;

    private Integer elapsedTime;

    private Float totalElevationGain;

    private LocalDateTime startDate;

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

    //Stores original value recived from sourced sport tracker.
    private String originalActivity;

    private SportActivity() {
        //neo4j purpose
    }


    SportActivity(String userUuid, String firstAndLastName, TrackerSource trackerSource, SportActivityType sportActivityType, Integer trackingSystemId, String externalId, String title, String description, Float distance, Integer movingTime, Integer elapsedTime, Float totalElevationGain, LocalDateTime startDate, String timezone, Boolean privateActivity, String gearId, Float averageSpeed, Float maxSpeed, Float averageCadence, Float averageTemp, Float averageWatts, Float weightedAverageWatts, Float kilojoules, Boolean deviceWatts, Boolean hasHeartrate, Float averageHeartrate, Integer maxHeartrate, Float calories, Float startLatitude, Float startLongitude, String deviceName, List<LatLng> coordinates, String originalActivity) {
        this.userUuid = userUuid;
        this.firstAndLastName = firstAndLastName;
        this.trackerSource = trackerSource;
        this.sportActivityType = sportActivityType;
        this.trackingSystemId = trackingSystemId;
        this.externalId = externalId;
        this.title = title;
        this.description = description;
        this.distance = distance;
        this.movingTime = movingTime;
        this.elapsedTime = elapsedTime;
        this.totalElevationGain = totalElevationGain;
        this.startDate = startDate;
        this.timezone = timezone;
        this.privateActivity = privateActivity;
        this.gearId = gearId;
        this.averageSpeed = averageSpeed;
        this.maxSpeed = maxSpeed;
        this.averageCadence = averageCadence;
        this.averageTemp = averageTemp;
        this.averageWatts = averageWatts;
        this.weightedAverageWatts = weightedAverageWatts;
        this.kilojoules = kilojoules;
        this.deviceWatts = deviceWatts;
        this.hasHeartrate = hasHeartrate;
        this.averageHeartrate = averageHeartrate;
        this.maxHeartrate = maxHeartrate;
        this.calories = calories;
        this.startLatitude = startLatitude;
        this.startLongitude = startLongitude;
        this.deviceName = deviceName;
        this.coordinates = coordinates;
    }

    public Long getId() {
        return id;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public String getFirstAndLastName() {
        return firstAndLastName;
    }

    public TrackerSource getTrackerSource() {
        return trackerSource;
    }

    public SportActivityType getSportActivityType() {
        return sportActivityType;
    }

    public Integer getTrackingSystemId() {
        return trackingSystemId;
    }

    public String getExternalId() {
        return externalId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Float getDistance() {
        return distance;
    }

    public Integer getMovingTime() {
        return movingTime;
    }

    public Integer getElapsedTime() {
        return elapsedTime;
    }

    public Float getTotalElevationGain() {
        return totalElevationGain;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public String getTimezone() {
        return timezone;
    }

    public Boolean getPrivateActivity() {
        return privateActivity;
    }

    public String getGearId() {
        return gearId;
    }

    public Float getAverageSpeed() {
        return averageSpeed;
    }

    public Float getMaxSpeed() {
        return maxSpeed;
    }

    public Float getAverageCadence() {
        return averageCadence;
    }

    public Float getAverageTemp() {
        return averageTemp;
    }

    public Float getAverageWatts() {
        return averageWatts;
    }

    public Float getWeightedAverageWatts() {
        return weightedAverageWatts;
    }

    public Float getKilojoules() {
        return kilojoules;
    }

    public Boolean getDeviceWatts() {
        return deviceWatts;
    }

    public Boolean getHasHeartrate() {
        return hasHeartrate;
    }

    public Float getAverageHeartrate() {
        return averageHeartrate;
    }

    public Integer getMaxHeartrate() {
        return maxHeartrate;
    }

    public Float getCalories() {
        return calories;
    }

    public Float getStartLatitude() {
        return startLatitude;
    }

    public Float getStartLongitude() {
        return startLongitude;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public List<LatLng> getCoordinates() {
        return coordinates;
    }

    public String getOriginalActivity() {
        return originalActivity;
    }
}
