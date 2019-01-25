package com.athleticspot.tracker.infrastracture.web;

import com.athleticspot.common.SecurityUtils;
import com.athleticspot.tracker.application.impl.AthleteApplicationServiceImpl;
import com.athleticspot.tracker.domain.graph.Athlete;
import com.athleticspot.tracker.domain.graph.GraphAthleteRepository;
import com.athleticspot.tracker.infrastracture.assembler.AthleteAssembler;
import com.athleticspot.tracker.infrastracture.web.dto.in.AthleteInDto;
import com.athleticspot.tracker.infrastracture.web.dto.in.FallowInDto;
import com.athleticspot.tracker.infrastracture.web.dto.out.AthleteOutDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PostMapping
    public void createAthlete(@RequestBody AthleteInDto athleteInDto) {
        graphAthleteRepository.save(new Athlete(athleteInDto.getName(), athleteInDto.getAthleteUuid(), athleteInDto.getFirstAndLastName()));
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
        final String currentUserLogin = SecurityUtils.getCurrentUserLogin();
        final Optional<Athlete> athleteOptional = graphAthleteRepository.findByName(currentUserLogin);

        return athleteApplicationServiceImpl.findAllFallowedAthletes(
            athleteOptional.orElseThrow(() -> new IllegalStateException("Athlete doesn't exist with name: " + currentUserLogin))
                .getAthleteUUID());
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

