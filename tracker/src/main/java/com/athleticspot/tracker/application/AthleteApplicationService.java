package com.athleticspot.tracker.application;

import com.athleticspot.tracker.domain.graph.Athlete;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * @author Tomasz Kasprzycki
 */
public interface AthleteApplicationService {

    void fallow(Long athleteIdToFallow);

    void unfallow(Long athleteIdToUnfallow);

    Page<Athlete> findAllFallowedAthletesPaged(String athleteUUID, PageRequest of);
}
