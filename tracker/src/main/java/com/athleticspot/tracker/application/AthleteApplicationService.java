package com.athleticspot.tracker.application;

import com.athleticspot.common.infrastracture.dto.AthleteUpdatedEventDto;
import com.athleticspot.tracker.domain.graph.Athlete;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * @author Tomasz Kasprzycki
 */
public interface AthleteApplicationService {

    void follow(Long athleteIdToFallow);

    void unfollow(Long athleteIdToUnfallow);

    Page<Athlete> findAllFallowedAthletesPaged(String athleteUUID, PageRequest of);

    List<Athlete> findAllFallowedAthletes(String athleteUuid);

    void createAthlete(String username, String uuid, String firstAndLastName);

    void updateAthlete(AthleteUpdatedEventDto athleteUpdatedEventDto);

    List<Long> checkIfFollow(List<Long> followedAthleteIdsToCheck, Long followingAthleteId);
}
