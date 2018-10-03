package com.athleticspot.tracker.application;

import com.athleticspot.tracker.domain.model.ManualSportActivity;
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

    List<ManualSportActivity> getSportActivities();

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
     * @param manualSportActivity
     */
    void removeActivity(ManualSportActivity manualSportActivity);

    /**
     *
     */
    void removeActivities();

    void createTimelineWithActivities();
}
