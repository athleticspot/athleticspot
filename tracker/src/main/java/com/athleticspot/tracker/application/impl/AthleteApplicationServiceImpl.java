package com.athleticspot.tracker.application.impl;

import com.athleticspot.common.SecurityUtils;
import com.athleticspot.common.domain.event.AthleteCreatedEvent;
import com.athleticspot.tracker.application.AthleteApplicationService;
import com.athleticspot.tracker.domain.graph.Athlete;
import com.athleticspot.tracker.domain.graph.GraphAthleteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;
import java.util.Optional;

/**
 * @author Tomasz Kasprzycki
 */
@Service
@Transactional
public class AthleteApplicationServiceImpl implements AthleteApplicationService {

    private final GraphAthleteRepository graphAthleteRepository;

    @Autowired
    public AthleteApplicationServiceImpl(GraphAthleteRepository graphAthleteRepository) {
        this.graphAthleteRepository = graphAthleteRepository;
    }

    @Override
    public void fallow(Long athleteIdToFallow) {
        final String currentUserLogin = SecurityUtils.getCurrentUserLogin();
        final Athlete athleteToFallow =
            graphAthleteRepository
                .findById(athleteIdToFallow)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Athlete with give athleteId: %s doesn't exists", athleteIdToFallow)));
        final Optional<Athlete> athleteOptional = graphAthleteRepository.findByName(currentUserLogin);
        final Athlete athlete = athleteOptional.orElseThrow(() -> new IllegalStateException(String.format("Athlete with name: %s doesn't exist", currentUserLogin)));
        athlete.fallow(athleteToFallow);
        graphAthleteRepository.save(athlete);

    }

    @Override
    public void unfallow(Long athleteIdToUnfallow) {
        final String currentUserLogin = SecurityUtils.getCurrentUserLogin();
        final Athlete athleteToUnfallow =
            graphAthleteRepository
                .findById(athleteIdToUnfallow)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Athlete with give athleteId: %s doesn't exists", athleteIdToUnfallow)));
        final Optional<Athlete> athleteOptional = graphAthleteRepository.findByName(currentUserLogin);
        final Athlete athlete = athleteOptional.orElseThrow(() -> new IllegalStateException(String.format("Athlete with name: %s doesn't exist", currentUserLogin)));
        athlete.unfallow(athleteToUnfallow);
        graphAthleteRepository.save(athlete);

    }

    @Override
    public Page<Athlete> findAllFallowedAthletesPaged(String athleteUUID, PageRequest pageRequest) {
        return graphAthleteRepository.findAllFallowedAthletesPaged(athleteUUID, pageRequest);
    }

    public List<Athlete> findAllFallowedAthletes(String athleteUuid) {
        return graphAthleteRepository.findAllFallowedAthletes(athleteUuid);
    }


    public void createAthlete(String username, String uuid) {
        Athlete athlete = new Athlete(username, uuid, "");
        graphAthleteRepository.save(athlete);
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleAthleteCreatedEvent(AthleteCreatedEvent athleteCreatedEvent) {
        createAthlete(
            athleteCreatedEvent.getContent().getName(),
            athleteCreatedEvent.getContent().getUuid()
        );
    }
}
