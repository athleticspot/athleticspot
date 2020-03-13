package com.athleticspot.tracker.infrastracture.security;

import com.athleticspot.common.SecurityUtils;
import com.athleticspot.tracker.domain.graph.Athlete;
import com.athleticspot.tracker.domain.graph.GraphAthleteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Tomasz Kasprzycki
 */
@Service
public class SecurityService {

    private final GraphAthleteRepository graphAthleteRepository;

    @Autowired
    public SecurityService(GraphAthleteRepository graphAthleteRepository) {
        this.graphAthleteRepository = graphAthleteRepository;
    }

    public Athlete getCurrentAthlete() {
        final String currentUserLogin = SecurityUtils.getCurrentUserLogin();
        return graphAthleteRepository.findByName(currentUserLogin)
            .orElseThrow(() -> new IllegalStateException(String.format("Athlete with name: %s doesn't exist", currentUserLogin)));
    }

    public Optional<Athlete> getAthleteByName(String name){
        return graphAthleteRepository.findByName(name);
    }
}
