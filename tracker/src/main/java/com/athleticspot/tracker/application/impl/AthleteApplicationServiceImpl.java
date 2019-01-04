package com.athleticspot.tracker.application.impl;

import com.athleticspot.common.domain.event.AthleteCreatedEvent;
import com.athleticspot.tracker.application.AthleteApplicationService;
import com.athleticspot.tracker.domain.graph.Athlete;
import com.athleticspot.tracker.domain.graph.GraphAthleteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;

/**
 * @author Tomasz Kasprzycki
 */
@Service
public class AthleteApplicationServiceImpl implements AthleteApplicationService {

    private final GraphAthleteRepository graphAthleteRepository;

    @Autowired
    public AthleteApplicationServiceImpl(GraphAthleteRepository graphAthleteRepository) {
        this.graphAthleteRepository = graphAthleteRepository;
    }

    @Override
    public void fallow(Long athleteId, Long athleteIdToFallow) {
        final Athlete athleteToFallow = graphAthleteRepository.findById(athleteIdToFallow)
            .orElseThrow(() ->
                new IllegalArgumentException(String.format("Athlete with give athleteId: %s doesn't exists", athleteIdToFallow)));
        graphAthleteRepository.findById(athleteId).ifPresent(athlete ->
            {
                athlete.fallow(athleteToFallow);
                graphAthleteRepository.save(athlete);
            }
        );
    }

    public List<Athlete> findAllFallowedAthletes(String athleteUuid){
        return graphAthleteRepository.findAllFallowedAthletes(athleteUuid);
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleAthleteCreatedEvent(AthleteCreatedEvent athleteCreatedEvent){
        System.out.println(athleteCreatedEvent);
    }


}
