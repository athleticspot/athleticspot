package com.athleticspot.tracker.infrastracture.assembler;

import com.athleticspot.tracker.domain.model.SportActivity;
import com.athleticspot.tracker.domain.model.TrackerSource;
import com.athleticspot.tracker.domain.model.manual.ManualSportActivity;
import org.springframework.stereotype.Component;

/**
 * @author Tomasz Kasprzycki
 */
@Component
public class ManualSportActivityAssembler implements SportActivityAssembler<ManualSportActivity> {

    @Override
    public SportActivity assembleSportActivity(ManualSportActivity manualSportActivity) {
        return new SportActivity()
            .setId(manualSportActivity.identifier())
            .setUsername(manualSportActivity.getUsername())
            .setTitle(manualSportActivity.details().title())
            .setTrackerSource(TrackerSource.MANUAL)
            .setStartDate(manualSportActivity.getStartDate());
    }
}
