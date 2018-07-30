package com.athleticspot.tracker.domain.model;

import com.athleticspot.tracker.domain.shared.Entity;
import org.springframework.util.Assert;

import java.util.Objects;

/**
 * @author Tomasz Kasprzycki
 */
public class SportActivity implements TimelineEvent, Entity {


    private final String sportyActivityIdentifier;

    public SportActivity(String sportActivityIdentifier) {
        Assert.notNull(sportActivityIdentifier, "Sport activity identifier cannot be null");
        this.sportyActivityIdentifier = sportActivityIdentifier;
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
}
