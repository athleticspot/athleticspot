package com.athleticspot.tracker.domain.graph;

import com.athleticspot.tracker.domain.model.SportActivityType;
import com.athleticspot.tracker.domain.model.TrackerSource;
import com.google.maps.model.LatLng;
import javastrava.api.v3.model.*;
import javastrava.api.v3.model.reference.StravaWorkoutType;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import java.time.LocalDateTime;
import java.util.Date;
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

    private SportActivity() {
        //neo4j purpose
    }

    private SportActivity(TrackerSource trackerSource, SportActivityType sportActivityType, Integer trackingSystemId, String externalId, Float distance, LocalDateTime startDate, StravaMapPoint startLatlng) {
        this.trackerSource = trackerSource;
        this.sportActivityType = sportActivityType;
        this.trackingSystemId = trackingSystemId;
        this.externalId = externalId;
        this.distance = distance;
        this.startDate = startDate;
    }

    public static SportActivity create(TrackerSource trackerSource,
                                       SportActivityType sportActivityType,
                                       Integer trackingSystemId,
                                       String externalId,
                                       Float distance,
                                       LocalDateTime startDate,
                                       StravaMapPoint startLatlng) {
        return new SportActivity(trackerSource, sportActivityType, trackingSystemId, externalId, distance, startDate, startLatlng);
    }

    public Long getId() {
        return id;
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

    public Float getDistance() {
        return distance;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }


}
