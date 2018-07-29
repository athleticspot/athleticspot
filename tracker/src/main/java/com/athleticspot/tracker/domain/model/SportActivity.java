package com.athleticspot.tracker.domain.model;

import com.athleticspot.tracker.domain.shared.Entity;
import org.springframework.util.Assert;

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
}
