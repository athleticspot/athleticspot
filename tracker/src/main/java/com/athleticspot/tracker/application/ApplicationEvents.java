package com.athleticspot.tracker.application;

import com.athleticspot.tracker.domain.model.Timeline;
import com.athleticspot.tracker.domain.model.manual.ManualSportActivity;

/**
 * @author Tomasz Kasprzycki
 */
public interface ApplicationEvents {

    void timelineWasCreated(Timeline timeline);

    void manualSportActivityAdded(ManualSportActivity manualSportActivity, String currentUserLogin);
}
