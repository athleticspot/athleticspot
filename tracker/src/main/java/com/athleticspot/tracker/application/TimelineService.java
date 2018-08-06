package com.athleticspot.tracker.application;

import com.athleticspot.tracker.domain.model.SportActivity;

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
     * @param sportActivity
     */
    void addActivity(SportActivity sportActivity);

    /**
     *
     */
    void addActivities();

    /**
     *
     * @param sportActivity
     */
    void removeActivity(SportActivity sportActivity);

    /**
     *
     */
    void removeActivities();

    void createTimelineWithActivities();
}
