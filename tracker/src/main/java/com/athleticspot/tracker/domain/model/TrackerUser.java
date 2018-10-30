package com.athleticspot.tracker.domain.model;

import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Tomasz Kasprzycki
 */
@Entity
@Table(name = "jhi_user")
public class TrackerUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "login")
    private String login;

    @Column(name = "timeline_id")
    private String timelineIdentifier;

    @Column(name = "strava_code")
    private String stravaCode;

    @Column(name = "strava_last_synchronization")
    private LocalDateTime stravaLastSynchronizationDate;

    public TrackerUser() {
    }

    public TrackerUser(String login, String timelineIdentifier) {
        this.login = login;
        this.timelineIdentifier = timelineIdentifier;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLogin() {
        return login;
    }

    public String getTimelineIdentifier() {
        return timelineIdentifier;
    }

    public String getStravaCode() {
        return stravaCode;
    }

    public LocalDateTime getStravaLastSynchronizationDate() {
        return stravaLastSynchronizationDate;
    }

    public void assignStravaCode(String stravaCode){
        this.stravaCode = stravaCode;
    }

    public TrackerUser assignTimelineIdentifier(String timelineIdentifier) {
        this.timelineIdentifier = timelineIdentifier;
        return this;
    }

    public TrackerUser assignStravaLastSynchronizationDate(LocalDateTime stravaLastSynchronizationDate){
        Assert.notNull(stravaLastSynchronizationDate, "Strava last synchronization date cannot be null");
        this.stravaLastSynchronizationDate = stravaLastSynchronizationDate;
        return this;
    }
}


