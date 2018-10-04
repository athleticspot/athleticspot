package com.athleticspot.tracker.infrastracture.assembler;

import com.athleticspot.tracker.domain.model.SportActivity;

/**
 * @author Tomasz Kasprzycki
 */
public interface SportActivityAssembler<T> {

    /**
     * @param trackerSportActivity
     * @param username
     * @return
     */
     SportActivity assembleSportActivity(T trackerSportActivity, String username);

}
