package com.athleticspot.tracker.application;

import com.athleticspot.tracker.domain.model.ManualSportActivity;
import com.athleticspot.tracker.domain.model.SportActivity;

/**
 * @author Tomasz Kasprzycki
 */
public interface ActivityAssembler {

    SportActivity buildFromManualSportActivity(ManualSportActivity manualSportActivity, String username);

}
