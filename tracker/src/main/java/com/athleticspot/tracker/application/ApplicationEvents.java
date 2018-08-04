package com.athleticspot.tracker.application;

import com.athleticspot.tracker.domain.model.SportActivity;
import com.athleticspot.tracker.domain.model.Timeline;

/**
 * @author Tomasz Kasprzycki
 */
public interface ApplicationEvents {

    void timelineWasCreated(Timeline timeline);

    void sportActivityAdded(SportActivity sportActivity);
}
