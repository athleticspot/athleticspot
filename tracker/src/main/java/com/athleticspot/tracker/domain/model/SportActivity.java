package com.athleticspot.tracker.domain.model;

import com.athleticspot.tracker.domain.shared.Entity;
import org.assertj.core.api.Assertions;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Tomasz Kasprzycki
 */
@javax.persistence.Entity
@Table(name = "sport_activity")
public class SportActivity implements TimelineEvent, Entity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "sport_activity_id")
    private final String sportyActivityIdentifier;

    @Embedded
    private final SportActivityDetails details;

    @Column(name = "source")
    private final String source;

    @Column(name = "timeline_id")
    private String timelineIdentifier;

    private SportActivity(String sportActivityIdentifier,
                          String sportActivitySource,
                          SportActivityDetails sportActivityDetails) {
        Assert.notNull(sportActivityIdentifier, "Sport activity identifier cannot be null");
        Assert.notNull(sportActivitySource, "Source cannot be null");
        Assert.notNull(sportActivityDetails, "Sport activity details cannot be null");
        this.sportyActivityIdentifier = sportActivityIdentifier;
        this.details = sportActivityDetails;
        this.source = sportActivitySource;
    }

    public static SportActivity create(String sportActivityIdentifier, String sportActivitySource, SportActivityDetails sportActivityDetails) {
        return new SportActivity(sportActivityIdentifier, sportActivitySource, sportActivityDetails);
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
        if (!(o instanceof SportActivity)) return false;
        SportActivity that = (SportActivity) o;
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
