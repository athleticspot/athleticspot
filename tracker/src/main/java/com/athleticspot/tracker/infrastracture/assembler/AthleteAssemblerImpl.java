package com.athleticspot.tracker.infrastracture.assembler;

import com.athleticspot.tracker.domain.graph.Athlete;
import com.athleticspot.tracker.domain.graph.GraphAthleteRepository;
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

    private static final int DEPTH = 1;
    private final SecurityService securityService;

    @Autowired
    public AthleteAssemblerImpl(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    public AthleteOutDto assembleAthlete(Athlete athlete) {
        return new AthleteOutDto(athlete.getName(),
            athlete.getAthleteUUID(),
            athlete.getId(),
            athlete.getFirstAndLastName(),
            securityService.getCurrentAthlete().checkIfFollowUser(athlete.getId()));
    }
}
