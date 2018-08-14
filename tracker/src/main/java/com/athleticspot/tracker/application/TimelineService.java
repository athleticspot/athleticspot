package com.athleticspot.tracker.application;

import com.athleticspot.tracker.domain.model.SportActivity;
import com.athleticspot.tracker.domain.model.SportActivityDetails;

import java.util.List;

/**
 * @author Tomasz Kasprzycki
 */

public interface TimelineService {

    /**
     * @return
     */
    String createTimeline();

    List<SportActivity> getSportActivities();

    /**
     *  @param sportActivityDetails
     * @param activitySource
     */
    String addActivity(SportActivityDetails sportActivityDetails, String activitySource);

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
