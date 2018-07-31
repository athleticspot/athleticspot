package com.athleticspot.tracker.domain.model;

import com.athleticspot.tracker.domain.shared.Entity;
import org.springframework.util.Assert;

import java.util.Objects;

/**
 * @author Tomasz Kasprzycki
 */
public class SportActivity implements TimelineEvent, Entity {


    private final String sportyActivityIdentifier;
    private final SportActivityDetails details;
    private final String source;

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
