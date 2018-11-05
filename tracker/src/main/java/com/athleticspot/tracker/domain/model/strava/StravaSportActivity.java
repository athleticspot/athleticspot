package com.athleticspot.tracker.domain.model.strava;

import com.athleticspot.tracker.domain.model.SportActivityGenericType;
import com.athleticspot.tracker.domain.model.SportActivityType;
import com.athleticspot.tracker.domain.model.TrackerSource;
import javastrava.api.v3.model.*;
import javastrava.api.v3.model.reference.StravaActivityType;
import javastrava.api.v3.model.reference.StravaWorkoutType;
import javastrava.api.v3.service.StreamService;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Tomasz Kasprzycki
 */
public class StravaSportActivity extends SportActivityGenericType {

    /**
     *athleticspot id
     */
    @Id
    @Field("id")
    private String id;

    /**
     * Indicates source of data (Stava, manual, etc...)
     */
    @Field("trackerSource")
    private TrackerSource trackerSource;

    /**
     * Athleticspot specific sport activity type
     */
    @Field("sportActivityType")
    private SportActivityType sportActivityType;
    /**
     * Strava original id
     */
    @Field("stravaId")
    private Integer stravaId;
    /**
     * The identifier given to the activity by the application that uploaded it
     */
    @Field("externalId")
    private String externalId;

    @Field("title")
    private String title;

    @Field("description")
    private String description;

    /**
     * Distance travelled in metres. If you want it in funny old imperial,
     * that's up to you to convert it
     */
    @Field("distance")
    private Float distance;

    /**
     * Total moving time in seconds.
     */
    @Field("movingTime")
    private Integer movingTime;
    /**
     * Total time including stopped time, in seconds
     */
    @Field("elapsedTime")
    private Integer elapsedTime;
    /**
     * Total elevation gain in metres
     */
    @Field("totalElevationGain")
    private Float totalElevationGain;

    /**
     * Date and time the activity was started
     */
    @Field("startDate")
    private LocalDateTime startDate;

    /**
     * Time zone
     */
    @Field("timezone")
    private String timezone;

    /**
     * Start location
     */
    @Field("startLatlng")
    private StravaMapPoint startLatlng;

    /**
     * End location
     */
    @Field("endLatlng")
    private StravaMapPoint endLatlng;


    /**
     * <p>
     * Weird map representation returned with the activity, basically contains
     * polylines for use on Google maps
     * </p>
     *
     * <p>
     * If you want the actual set of GPS coordinates of the activity, then you
     * need to use
     * {@link StreamService#getActivityStreams(Integer, javastrava.api.v3.model.reference.StravaStreamResolutionType, javastrava.api.v3.model.reference.StravaStreamSeriesDownsamplingType, javastrava.api.v3.model.reference.StravaStreamType...)}
     * </p>
     */
    @Field("map")
    private StravaMap map;

    /**
     * Is set to <code>true</code> if the activity was manually entered into
     * Strava, rather than being uploaded as a file from some GPS device, or
     * your phone</p>
     */
    @Field("manual")
    private Boolean manual;

    /**
     * Is set to <code>true</code> if the activity has been flagged as private
     * by the athlete
     */
    @Field("privateActivity")
    private Boolean privateActivity;

    @Field("workoutType")
    private StravaWorkoutType workoutType;

    /**
     * Unique identifier of the {@link StravaGear} used on this activity
     */
    @Field("gearId")
    private String gearId;
    /**
     * Summary representation of the gear used for the activity
     */
    @Field("gear")
    private StravaGear gear;
    /**
     * Average speed (in metres per second) of the activity (as calculated by
     * Strava; is not recalculated or checked by javastrava)
     */
    @Field("averageSpeed")
    private Float averageSpeed;
    /**
     * Maximum speed (in metres per second) achieved during the activity (quite
     * often as a result of GPS inaccuracies). Calculated by Strava and not
     * recalculated or checked by javastrava.
     */
    @Field("maxSpeed")
    private Float maxSpeed;
    /**
     * Average RPM if cadence data was provided with the uploaded activity
     */
    @Field("averageCadence")
    private Float averageCadence;
    /**
     * Average temperature (in degrees Celsius) if temperature data was provided
     * with the uploaded activity
     */
    @Field("averageTemp")
    private Float averageTemp;
    /**
     * Average power (in watts) for rides only. Strava calculates an estimate
     * for this if power meter data is not provided with the upload.
     */
    @Field("averageWatts")
    private Float averageWatts;
    /**
     * Weighted average power (in watts) for rides with power meter data only.
     */
    @Field("weightedAverageWatts")
    private Float weightedAverageWatts;
    /**
     * Total energy expended by the rider in kilojoules
     */
    @Field("kilojoules")
    private Float kilojoules;
    /**
     * Is set to <code>true</code> if power meter data was provided with the
     * upload
     */
    @Field("deviceWatts")
    private Boolean deviceWatts;

    /**
     * Is set to <code>true</code> if the activity was recorded with heartrate
     */
    @Field("hasHeartrate")
    private Boolean hasHeartrate;
    /**
     * Average heart rate (in beats per minute) if heart rate data was provided
     * with the upload
     */
    @Field("averageHeartrate")
    private Float averageHeartrate;
    /**
     * Maximum heart rate (in beats per minute) if heart rate data was provided
     * with the upload
     */
    @Field("maxHeartrate")
    private Integer maxHeartrate;
    /**
     * Kilocalories expended (calculated by Strava)
     */
    @Field("calories")
    private Float calories;

    /**
     * Is set to <code>true</code> if the currently authenticated athlete has
     * kudoed this activity
     */
    @Field("hasKudoed")
    private Boolean hasKudoed;
    /**
     * Segment efforts associated with the activity
     */
    @Field("segmentEfforts")
    private List<StravaSegmentEffort> segmentEfforts;
    /**
     * Runs only - list of metric splits
     */
    @Field("splitsMetric")
    private List<StravaSplit> splitsMetric;
    /**
     * Runs only - list of imperial splits ("standard" hahahaha you Americans
     * are so funny)
     */
    @Field("splitsStandard")
    private List<StravaSplit> splitsStandard;
    /**
     * Runs only - list of best efforts
     */
    @Field("bestEfforts")
    private List<StravaBestRunningEffort> bestEfforts;

    /**
     * Identifier of the original upload
     */
    @Field("uploadId")
    private Integer uploadId;

    /**
     * Latitude of the start point of the activity
     */
    @Field("startLatitude")
    private Float startLatitude;

    /**
     * Longitude of the start point of the activity
     */
    @Field("startLongitude")
    private Float startLongitude;
    /**
     * Instagram primary photo
     */
    @Field("instagramPrimaryPhoto")
    private String instagramPrimaryPhoto;
    /**
     * Slightly weird summaries of the photos associated with the activity
     */
    @Field("photos")
    private StravaActivityPhotos photos;

    /**
     * Seems to be the video used when doing the activity
     */
    @Field("video")
    private StravaVideo video;

    /**
     * The token used to construct an embed URL in the form <a href="https://www.strava.com/activities/[ACTIVITY_ID]/embed/[embedId]">https://www.strava.com/activities/[ACTIVITY_ID]/embed/[embedId]</a>
     */
    @Field("embedToken")
    private String embedToken;

    /**
     * The name of the device used to record the activity
     */
    @Field("deviceName")
    private String deviceName;

    public static StravaSportActivity creteFromStravaActivity(final StravaActivity stravaActivity, String username) {
        return new StravaSportActivity(
            stravaActivity,
            username
        );
    }

    public StravaSportActivity() {
    }

    private StravaSportActivity(StravaActivity stravaActivity, String username) {
        this.setUsername(username)
            .setTrackerSource(TrackerSource.STRAVA)
            .setSportActivityType(stravaActivity.getType())
            .setStravaId(stravaActivity.getId())
            .setExternalId(stravaActivity.getExternalId())
            .setTitle(stravaActivity.getName())
            .setDescription(stravaActivity.getDescription())
            .setDistance(stravaActivity.getDistance())
            .setMovingTime(stravaActivity.getMovingTime())
            .setElapsedTime(stravaActivity.getElapsedTime())
            .setTotalElevationGain(stravaActivity.getTotalElevationGain())
            .setStartDate(stravaActivity.getStartDateLocal())
            .setTimezone(stravaActivity.getTimezone())
            .setStartLatlng(stravaActivity.getStartLatlng())
            .setEndLatlng(stravaActivity.getEndLatlng())
            .setMap(stravaActivity.getMap())
            .setManual(stravaActivity.getManual())
            .setPrivateActivity(stravaActivity.getPrivateActivity())
            .setWorkoutType(stravaActivity.getWorkoutType())
            .setGearId(stravaActivity.getGearId())
            .setGear(stravaActivity.getGear())
            .setAverageSpeed(stravaActivity.getAverageSpeed())
            .setMaxSpeed(stravaActivity.getMaxSpeed())
            .setAverageCadence(stravaActivity.getAverageCadence())
            .setAverageTemp(stravaActivity.getAverageTemp())
            .setAverageWatts(stravaActivity.getAverageWatts())
            .setKilojoules(stravaActivity.getKilojoules())
            .setDeviceWatts(stravaActivity.getDeviceWatts())
            .setHasHeartrate(stravaActivity.getHasHeartrate())
            .setAverageHeartrate(stravaActivity.getAverageHeartrate())
            .setMaxHeartrate(stravaActivity.getMaxHeartrate())
            .setCalories(stravaActivity.getCalories())
            .setHasKudoed(stravaActivity.getHasKudoed())
            .setSegmentEfforts(stravaActivity.getSegmentEfforts())
            .setSplitsMetric(stravaActivity.getSplitsMetric())
            .setSplitsMetric(stravaActivity.getSplitsMetric())
            .setBestEfforts(stravaActivity.getBestEfforts())
            .setUploadId(stravaActivity.getUploadId())
            .setStartLongitude(stravaActivity.getStartLongitude())
            .setInstagramPrimaryPhoto(stravaActivity.getInstagramPrimaryPhoto())
            .setPhotos(stravaActivity.getPhotos())
            .setVideo(stravaActivity.getVideo())
            .setEmbedToken(stravaActivity.getEmbedToken())
            .setDeviceName(stravaActivity.getDeviceName());
    }

    //Getters

    public String getId() {
        return id;
    }

    public TrackerSource getTrackerSource() {
        return trackerSource;
    }

    public SportActivityType getSportActivityType() {
        return sportActivityType;
    }

    public Integer getStravaId() {
        return stravaId;
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

    public StravaMapPoint getStartLatlng() {
        return startLatlng;
    }

    public StravaMapPoint getEndLatlng() {
        return endLatlng;
    }

    public StravaMap getMap() {
        return map;
    }

    public Boolean getManual() {
        return manual;
    }

    public Boolean getPrivateActivity() {
        return privateActivity;
    }

    public StravaWorkoutType getWorkoutType() {
        return workoutType;
    }

    public String getGearId() {
        return gearId;
    }

    public StravaGear getGear() {
        return gear;
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

    public Boolean getHasKudoed() {
        return hasKudoed;
    }

    public List<StravaSegmentEffort> getSegmentEfforts() {
        return segmentEfforts;
    }

    public List<StravaSplit> getSplitsMetric() {
        return splitsMetric;
    }

    public List<StravaSplit> getSplitsStandard() {
        return splitsStandard;
    }

    public List<StravaBestRunningEffort> getBestEfforts() {
        return bestEfforts;
    }

    public Integer getUploadId() {
        return uploadId;
    }

    public Float getStartLatitude() {
        return startLatitude;
    }

    public Float getStartLongitude() {
        return startLongitude;
    }

    public String getInstagramPrimaryPhoto() {
        return instagramPrimaryPhoto;
    }

    public StravaActivityPhotos getPhotos() {
        return photos;
    }

    public StravaVideo getVideo() {
        return video;
    }

    public String getEmbedToken() {
        return embedToken;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getUsername() {
        return username;
    }

//Fluent interface methods

    public StravaSportActivity setTrackerSource(final TrackerSource trackerSource) {
        this.trackerSource = trackerSource;
        return this;
    }

    public StravaSportActivity setSportActivityType(final StravaActivityType sportActivityType) {
        //TODO: check if it works
        this.sportActivityType = SportActivityType.valueOf(sportActivityType.name());
        return this;
    }

    public StravaSportActivity setStravaId(final Integer stravaId) {
        this.stravaId = stravaId;
        return this;
    }

    public StravaSportActivity setExternalId(final String externalId) {
        this.externalId = externalId;
        return this;
    }

    public StravaSportActivity setTitle(final String title) {
        this.title = title;
        return this;
    }

    public StravaSportActivity setDescription(final String description) {
        this.description = description;
        return this;
    }

    public StravaSportActivity setDistance(final Float distance) {
        this.distance = distance;
        return this;
    }

    public StravaSportActivity setMovingTime(final Integer movingTime) {
        this.movingTime = movingTime;
        return this;
    }

    public StravaSportActivity setElapsedTime(final Integer elapsedTime) {
        this.elapsedTime = elapsedTime;
        return this;
    }

    public StravaSportActivity setTotalElevationGain(final Float totalElevationGain) {
        this.totalElevationGain = totalElevationGain;
        return this;
    }

    public StravaSportActivity setStartDate(final LocalDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    public StravaSportActivity setTimezone(final String timezone) {
        this.timezone = timezone;
        return this;
    }


    public StravaSportActivity setStartLatlng(final StravaMapPoint startLatlng) {
        this.startLatlng = startLatlng;
        return this;
    }

    public StravaSportActivity setEndLatlng(final StravaMapPoint endLatlng) {
        this.endLatlng = endLatlng;
        return this;
    }

    public StravaSportActivity setMap(final StravaMap map) {
        this.map = map;
        return this;
    }

    public StravaSportActivity setManual(final Boolean manual) {
        this.manual = manual;
        return this;
    }

    public StravaSportActivity setPrivateActivity(final Boolean privateActivity) {
        this.privateActivity = privateActivity;
        return this;
    }

    public StravaSportActivity setWorkoutType(final StravaWorkoutType workoutType) {
        this.workoutType = workoutType;
        return this;
    }

    public StravaSportActivity setGearId(final String gearId) {
        this.gearId = gearId;
        return this;
    }

    public StravaSportActivity setGear(final StravaGear gear) {
        this.gear = gear;
        return this;
    }

    public StravaSportActivity setAverageSpeed(final Float averageSpeed) {
        this.averageSpeed = averageSpeed;
        return this;
    }

    public StravaSportActivity setMaxSpeed(final Float maxSpeed) {
        this.maxSpeed = maxSpeed;
        return this;
    }

    public StravaSportActivity setAverageCadence(final Float averageCadence) {
        this.averageCadence = averageCadence;
        return this;
    }

    public StravaSportActivity setAverageTemp(final Float averageTemp) {
        this.averageTemp = averageTemp;
        return this;
    }

    public StravaSportActivity setAverageWatts(final Float averageWatts) {
        this.averageWatts = averageWatts;
        return this;
    }

    public StravaSportActivity setWeightedAverageWatts(final Float weightedAverageWatts) {
        this.weightedAverageWatts = weightedAverageWatts;
        return this;
    }

    public StravaSportActivity setKilojoules(final Float kilojoules) {
        this.kilojoules = kilojoules;
        return this;
    }

    public StravaSportActivity setDeviceWatts(final Boolean deviceWatts) {
        this.deviceWatts = deviceWatts;
        return this;
    }

    public StravaSportActivity setHasHeartrate(final Boolean hasHeartrate) {
        this.hasHeartrate = hasHeartrate;
        return this;
    }

    public StravaSportActivity setAverageHeartrate(final Float averageHeartrate) {
        this.averageHeartrate = averageHeartrate;
        return this;
    }

    public StravaSportActivity setMaxHeartrate(final Integer maxHeartrate) {
        this.maxHeartrate = maxHeartrate;
        return this;
    }

    public StravaSportActivity setCalories(final Float calories) {
        this.calories = calories;
        return this;
    }

    public StravaSportActivity setHasKudoed(final Boolean hasKudoed) {
        this.hasKudoed = hasKudoed;
        return this;
    }

    public StravaSportActivity setSegmentEfforts(final List<StravaSegmentEffort> segmentEfforts) {
        this.segmentEfforts = segmentEfforts;
        return this;
    }

    public StravaSportActivity setSplitsMetric(final List<StravaSplit> splitsMetric) {
        this.splitsMetric = splitsMetric;
        return this;
    }

    public StravaSportActivity setSplitsStandard(final List<StravaSplit> splitsStandard) {
        this.splitsStandard = splitsStandard;
        return this;
    }

    public StravaSportActivity setBestEfforts(final List<StravaBestRunningEffort> bestEfforts) {
        this.bestEfforts = bestEfforts;
        return this;
    }

    public StravaSportActivity setUploadId(final Integer uploadId) {
        this.uploadId = uploadId;
        return this;
    }

    public StravaSportActivity setStartLatitude(final Float startLatitude) {
        this.startLatitude = startLatitude;
        return this;
    }

    public StravaSportActivity setStartLongitude(final Float startLongitude) {
        this.startLongitude = startLongitude;
        return this;
    }

    public StravaSportActivity setInstagramPrimaryPhoto(final String instagramPrimaryPhoto) {
        this.instagramPrimaryPhoto = instagramPrimaryPhoto;
        return this;
    }

    public StravaSportActivity setPhotos(final StravaActivityPhotos photos) {
        this.photos = photos;
        return this;
    }

    public StravaSportActivity setVideo(final StravaVideo video) {
        this.video = video;
        return this;
    }

    public StravaSportActivity setEmbedToken(final String embedToken) {
        this.embedToken = embedToken;
        return this;
    }

    public StravaSportActivity setDeviceName(final String deviceName) {
        this.deviceName = deviceName;
        return this;
    }

    public StravaSportActivity setUsername(final String username) {
        this.username = username;
        return this;
    }


}
