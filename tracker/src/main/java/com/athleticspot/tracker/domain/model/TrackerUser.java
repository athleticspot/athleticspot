package com.athleticspot.tracker.domain.model;

import javax.persistence.*;

/**
 * @author Tomasz Kasprzycki
 */
@Entity
@Table(name = "jhi_user")
public class TrackerUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "timeline_id")
    private String timelineIdentifier;

    @Column(name = "strava_code")
    private String stravaCode;

    public TrackerUser() {
    }

    public TrackerUser(String login, String timelineIdentifier) {
        this.login = login;
        this.timelineIdentifier = timelineIdentifier;
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

    public void assignStravaCode(String stravaCode){
        this.stravaCode = stravaCode;
    }

    public TrackerUser assignTimelineIdentifier(String timelineIdentifier) {
        this.timelineIdentifier = timelineIdentifier;
        return this;
    }
}


