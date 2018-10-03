package com.athleticspot.tracker.domain.model;

import com.athleticspot.tracker.domain.shared.Entity;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Tomasz Kasprzycki
 */
@javax.persistence.Entity
@Table(name = "sport_activity")
public class ManualSportActivity implements TimelineEvent, Entity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "sport_activity_id")
    private String sportyActivityIdentifier;

    @Embedded
    private SportActivityDetails details;

    @Column(name = "source")
    private String source;

    @Column(name = "timeline_id")
    private String timelineIdentifier;

    private ManualSportActivity() {
        //jpa purpose
    }

    private ManualSportActivity(String sportActivityIdentifier,
                                String sportActivitySource,
                                SportActivityDetails sportActivityDetails) {
        Assert.notNull(sportActivityIdentifier, "Sport activity identifier cannot be null");
        Assert.notNull(sportActivitySource, "Source cannot be null");
        Assert.notNull(sportActivityDetails, "Sport activity details cannot be null");
        this.sportyActivityIdentifier = sportActivityIdentifier;
        this.details = sportActivityDetails;
        this.source = sportActivitySource;
    }

    private ManualSportActivity(String sportyActivitySource, SportActivityDetails sportActivityDetails) {
        Assert.notNull(sportActivityDetails, "Sport activity details cannot be null");
        Assert.notNull(sportyActivitySource, "Sport activity source cannot be null");
        this.source = sportyActivitySource;
        this.details = sportActivityDetails;
    }

    public static ManualSportActivity create(String sportActivityIdentifier, String sportActivitySource, SportActivityDetails sportActivityDetails) {
        return new ManualSportActivity(sportActivityIdentifier, sportActivitySource, sportActivityDetails);
    }

    public static ManualSportActivity createNew(String sportyActivitySource, SportActivityDetails sportActivityDetails) {
        return new ManualSportActivity(sportyActivitySource, sportActivityDetails);
    }

    @Override
    public String identifier() {
        return sportyActivityIdentifier;
    }

    @Override
    public void assignToTimeline(String timelineIdentifier) {
        Assert.notNull(timelineIdentifier, "timeline identifier cannot be null");
        this.timelineIdentifier = timelineIdentifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ManualSportActivity)) return false;
        ManualSportActivity that = (ManualSportActivity) o;
        return Objects.equals(sportyActivityIdentifier, that.sportyActivityIdentifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sportyActivityIdentifier);
    }

    public SportActivityDetails details() {
        return this.details;
    }

    public String source() {
        return this.source;
    }
}
