package com.athleticspot.tracker.domain.model;

import org.springframework.util.Assert;

/**
 * @author Tomasz Kasprzycki
 */
public class ApplicationUserId {

    private final String userId;

    private ApplicationUserId(String userId) {
        this.userId = userId;
    }

    public static ApplicationUserId create(String applicationUserId) {
        Assert.notNull(applicationUserId, "Application user Id cannot be null");
        return new ApplicationUserId(applicationUserId);
    }
}
