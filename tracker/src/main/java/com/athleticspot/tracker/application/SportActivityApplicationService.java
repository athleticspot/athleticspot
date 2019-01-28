package com.athleticspot.tracker.application;

import com.athleticspot.tracker.infrastracture.web.dto.in.SportActivityInDto;

/**
 * @author Tomasz Kasprzycki
 */
public interface SportActivityApplicationService {

    void createSportActivity(SportActivityInDto sportActivityInDto);
}
