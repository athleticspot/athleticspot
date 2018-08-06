package com.athleticspot.tracker.application;

import com.athleticspot.tracker.domain.model.SportActivity;
import com.athleticspot.tracker.domain.model.Timeline;
import org.springframework.stereotype.Service;

/**
 * @author Tomasz Kasprzycki
 */
@Service
public class ApplicationEventsImpl implements ApplicationEvents {

    @Override
    public void timelineWasCreated(Timeline timeline) {

    }

    @Override
    public void sportActivityAdded(SportActivity sportActivity) {

    }
}
