package com.athleticspot.tracker.application.impl;

import com.athleticspot.tracker.application.ApplicationEvents;
import com.athleticspot.tracker.domain.model.GenericSportActivityRepository;
import com.athleticspot.tracker.domain.model.Timeline;
import com.athleticspot.tracker.domain.model.manual.ManualSportActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tomasz Kasprzycki
 */
@Service
public class ApplicationEventsImpl implements ApplicationEvents {

    private final GenericSportActivityRepository genericSportActivityRepository;

    @Autowired
    public ApplicationEventsImpl(GenericSportActivityRepository genericSportActivityRepository) {
        this.genericSportActivityRepository = genericSportActivityRepository;
    }

    @Override
    public void timelineWasCreated(Timeline timeline) {

    }

    @Override
    public void manualSportActivityAdded(ManualSportActivity manualSportActivity, String username) {
        manualSportActivity.assignOwner(username);
        genericSportActivityRepository.save(manualSportActivity);
    }
}
