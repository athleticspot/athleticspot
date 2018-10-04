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
    public SportActivity assembleSportActivity(ManualSportActivity trackerSportActivity, String username) {
        return SportActivity.create(
            username,
            trackerSportActivity.details().title(),
            TrackerSource.MANUAL
        );
    }
}
