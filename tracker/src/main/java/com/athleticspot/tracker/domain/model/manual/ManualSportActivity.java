package com.athleticspot.tracker.domain.model.manual;

import com.athleticspot.tracker.domain.model.SportActivityGenericType;
import com.athleticspot.tracker.domain.model.TimelineEvent;
import com.athleticspot.tracker.domain.shared.Entity;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Tomasz Kasprzycki
 */
@javax.persistence.Entity
@Table(name = "sport_activity")
public class ManualSportActivity extends SportActivityGenericType implements TimelineEvent, Entity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "sport_activity_id")
    private String sportyActivityIdentifier;

    @Embedded
    private ManualSportActivityDetails details;

    @Column(name = "source")
    private String source;

    @Column(name = "timeline_id")
    private String timelineIdentifier;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    private ManualSportActivity() {
        //jpa purpose
    }

    private ManualSportActivity(String sportActivityIdentifier,
                                String sportActivitySource,
                                ManualSportActivityDetails manualSportActivityDetails,
                                LocalDateTime startDate) {
        Assert.notNull(sportActivityIdentifier, "Sport activity identifier cannot be null");
        Assert.notNull(sportActivitySource, "Source cannot be null");
        Assert.notNull(manualSportActivityDetails, "Sport activity details cannot be null");
        Assert.notNull(startDate, "Start date cannot be null");
        this.sportyActivityIdentifier = sportActivityIdentifier;
        this.details = manualSportActivityDetails;
        this.source = sportActivitySource;
        this.startDate = startDate;
    }

    private ManualSportActivity(String sportyActivitySource,
                                ManualSportActivityDetails manualSportActivityDetails,
                                LocalDateTime startDate) {
        Assert.notNull(manualSportActivityDetails, "Sport activity details cannot be null");
        Assert.notNull(sportyActivitySource, "Sport activity source cannot be null");
        Assert.notNull(startDate, "Start date cannot be null");
        this.source = sportyActivitySource;
        this.details = manualSportActivityDetails;
        this.startDate = startDate;
    }

    public static ManualSportActivity create(String sportActivityIdentifier,
                                             String sportActivitySource,
                                             ManualSportActivityDetails manualSportActivityDetails,
                                             LocalDateTime startDate) {
        return new ManualSportActivity(sportActivityIdentifier, sportActivitySource, manualSportActivityDetails, startDate);
    }

    public static ManualSportActivity createNew(String sportyActivitySource,
                                                ManualSportActivityDetails manualSportActivityDetails,
                                                LocalDateTime startDate) {
        return new ManualSportActivity(sportyActivitySource, manualSportActivityDetails, startDate);
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

    public void assignOwner(String username) {
        this.username = username;
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

    public ManualSportActivityDetails details() {
        return this.details;
    }

    public String source() {
        return this.source;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }
}
