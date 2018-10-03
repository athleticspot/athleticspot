package com.athleticspot.tracker.application.impl;

import com.athleticspot.tracker.application.ActivityAssembler;
import com.athleticspot.tracker.application.ApplicationEvents;
import com.athleticspot.tracker.domain.model.GeneralSportActivityRepository;
import com.athleticspot.tracker.domain.model.ManualSportActivity;
import com.athleticspot.tracker.domain.model.Timeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tomasz Kasprzycki
 */
@Service
public class ApplicationEventsImpl implements ApplicationEvents {

    private final GeneralSportActivityRepository generalSportActivityRepository;

    private final ActivityAssembler activityAssembler;

    @Autowired
    public ApplicationEventsImpl(GeneralSportActivityRepository generalSportActivityRepository, ActivityAssembler activityAssembler) {
        this.generalSportActivityRepository = generalSportActivityRepository;
        this.activityAssembler = activityAssembler;
    }

    @Override
    public void timelineWasCreated(Timeline timeline) {

    }

    @Override
    public void manualSportActivityAdded(ManualSportActivity manualSportActivity, String username) {
        generalSportActivityRepository.save(
            activityAssembler.buildFromManualSportActivity(
                manualSportActivity,
                username
            )
        );
    }
}
