package com.athleticspot.tracker.infrastracture.web;

import com.athleticspot.tracker.domain.graph.Actor;
import com.athleticspot.tracker.domain.graph.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tomasz Kasprzycki
 */
@RestController
@RequestMapping(value = SportTrackersApiUrl.API + "/actor")
public class ActorController {


    private final ActorRepository actorRepository;

    @Autowired
    public ActorController(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    @GetMapping
    public Iterable<Actor> getActors(){
        return actorRepository.findAll();
    }
}
