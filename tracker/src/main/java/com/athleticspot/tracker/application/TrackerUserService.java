package com.athleticspot.tracker.application;

/**
 * @author Tomasz Kasprzycki
 */
public interface TrackerUserService {

    String getTimelineIdentifier();

    void addTimelineIdentifier(String availableTimelineId);

}
