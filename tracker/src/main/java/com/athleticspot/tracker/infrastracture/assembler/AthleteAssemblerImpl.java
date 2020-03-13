package com.athleticspot.tracker.infrastracture.assembler;

import com.athleticspot.tracker.domain.graph.Athlete;
import com.athleticspot.tracker.domain.model.TrackerUser;
import com.athleticspot.tracker.domain.model.TrackerUserRepository;
import com.athleticspot.tracker.infrastracture.security.SecurityService;
import com.athleticspot.tracker.infrastracture.web.dto.out.AthleteOutDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Tomasz Kasprzycki
 */
@Service
public class AthleteAssemblerImpl implements AthleteAssembler {

    private final SecurityService securityService;

    private final TrackerUserRepository trackerUserRepository;

    @Autowired
    public AthleteAssemblerImpl(SecurityService securityService, TrackerUserRepository trackerUserRepository) {
        this.securityService = securityService;
        this.trackerUserRepository = trackerUserRepository;
    }

    @Override
    public AthleteOutDto assembleAthlete(Athlete athlete) {
        final String login = athlete.getName();
        String imageUrl = null;
        final Optional<TrackerUser> trackerUserOptional = trackerUserRepository.findByLogin(login);
        if (trackerUserOptional.isPresent()) {
            imageUrl = trackerUserOptional.get().getImageUrl();
        }
        return new AthleteOutDto(login,
            athlete.getAthleteUUID(),
            athlete.getId(),
            athlete.getFirstAndLastName(),
            securityService.getCurrentAthlete().checkIfFollowUser(athlete.getId()),
            imageUrl);
    }
}
