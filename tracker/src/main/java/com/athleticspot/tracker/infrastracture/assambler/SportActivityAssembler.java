package com.athleticspot.tracker.infrastracture.assambler;

import com.athleticspot.tracker.application.ActivityAssembler;
import com.athleticspot.tracker.domain.model.ManualSportActivity;
import com.athleticspot.tracker.domain.model.SportActivity;
import com.athleticspot.tracker.domain.model.TrackerSource;
import org.springframework.stereotype.Component;

/**
 * @author Tomasz Kasprzycki
 */
@Component
public class SportActivityAssembler implements ActivityAssembler {

    @Override
    public SportActivity buildFromManualSportActivity(ManualSportActivity manualSportActivity, String username) {
        return SportActivity.create(
            username,
            manualSportActivity.details().title(),
            TrackerSource.MANUAL
        );
    }
}
