package com.athleticspot.tracker.application;

/**
 * @author Tomasz Kasprzycki
 */

public interface TimelineService {

    /**
     * @return
     */
    String createTimeline();

    /**
     *
     */
    void addActivity();

    /**
     *
     */
    void addActivities();

    /**
     *
     */
    void removeActivity();

    /**
     *
     */
    void removeActivities();

}
