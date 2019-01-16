package com.athleticspot.tracker.infrastracture.web;

import com.athleticspot.tracker.application.impl.AthleteApplicationServiceImpl;
import com.athleticspot.tracker.domain.graph.Athlete;
import com.athleticspot.tracker.domain.graph.GraphAthleteRepository;
import com.athleticspot.tracker.infrastracture.assembler.AthleteAssembler;
import com.athleticspot.tracker.infrastracture.web.dto.in.FallowInDto;
import com.athleticspot.tracker.infrastracture.web.dto.out.AthleteOutDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Tomasz Kasprzycki
 */
@RestController
@RequestMapping(value = SportTrackersApiUrl.API + "/athlete")
public class AthleteController {

    private final AthleteApplicationServiceImpl athleteApplicationServiceImpl;

    private final GraphAthleteRepository graphAthleteRepository;

    private final AthleteAssembler athleteAssembler;

    public AthleteController(AthleteApplicationServiceImpl athleteApplicationServiceImpl,
                             GraphAthleteRepository graphAthleteRepository,
                             AthleteAssembler athleteAssembler) {
        this.athleteApplicationServiceImpl = athleteApplicationServiceImpl;
        this.graphAthleteRepository = graphAthleteRepository;
        this.athleteAssembler = athleteAssembler;
    }

    @GetMapping
    public Page<AthleteOutDto> searchForAthletes(@RequestParam String name,
                                                 @RequestParam int page,
                                                 @RequestParam int pageSize) {

        final Page<Athlete> athletesPaged =
            graphAthleteRepository.findAthletes(
                name,
                PageRequest.of(page, pageSize)
            );
        return athletesPaged.map(athleteAssembler::assembleAthlete);
    }

    @GetMapping("/fallowed")
    public List<Athlete> findAllFallowedAthletes() {
        return athleteApplicationServiceImpl.findAllFallowedAthletes("");
    }

    @PutMapping(value = "/fallow")
    public void fallow(@RequestBody FallowInDto fallowInDto) {
        athleteApplicationServiceImpl.fallow(
            Long.parseLong(fallowInDto.getAthleteId()),
            Long.parseLong(fallowInDto.getAthleteIdToFallow())
        );
    }

    @DeleteMapping(value = "/fallow")
    public void unfallow() {

    }


}

