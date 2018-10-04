package com.athleticspot.tracker.application;

import com.athleticspot.tracker.domain.model.manual.ManualSportActivity;
import com.athleticspot.tracker.domain.model.manual.ManualSportActivityDetails;

import java.util.List;

/**
 * @author Tomasz Kasprzycki
 */

public interface TimelineService {

    String createTimeline();

    List<ManualSportActivity> getManualSportActivities();

    String addActivity(ManualSportActivityDetails manualSportActivityDetails, String activitySource);

    void addActivities();

    void removeActivity(ManualSportActivity manualSportActivity);

    void removeActivities();

    void createTimelineWithActivities();
}
