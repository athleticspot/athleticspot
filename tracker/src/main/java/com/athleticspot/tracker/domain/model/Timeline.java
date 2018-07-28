package com.athleticspot.tracker.domain.model;

/**
 * @author Tomasz Kasprzycki
 */
public class Timeline {

    private String timelineIdentifier;
    private final ApplicationUserId applicationUserId;

    private Timeline(ApplicationUserId applicationUserId, String timelineIdentifier) {
        this.applicationUserId = applicationUserId;
        this.timelineIdentifier = timelineIdentifier;
    }

    public static Timeline create(ApplicationUserId applicationUserId, String timelineIdentifier) {
        return new Timeline(applicationUserId, timelineIdentifier);
    }

    public ApplicationUserId user() {
        return applicationUserId;

    }

    public String timelineIdentifier() {
        return this.timelineIdentifier;
    }
}
