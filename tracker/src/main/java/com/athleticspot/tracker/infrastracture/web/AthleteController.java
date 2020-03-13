package com.athleticspot.tracker.infrastracture.web;

import com.athleticspot.common.AuthoritiesConstants;
import com.athleticspot.common.SecurityUtils;
import com.athleticspot.tracker.application.impl.AthleteApplicationServiceImpl;
import com.athleticspot.tracker.domain.graph.Athlete;
import com.athleticspot.tracker.domain.graph.GraphAthleteRepository;
import com.athleticspot.tracker.infrastracture.assembler.AthleteAssembler;
import com.athleticspot.tracker.infrastracture.security.SecurityService;
import com.athleticspot.tracker.infrastracture.web.dto.in.AthleteInDto;
import com.athleticspot.tracker.infrastracture.web.dto.in.FollowAthleteInDto;
import com.athleticspot.tracker.infrastracture.web.dto.in.FollowedInDto;
import com.athleticspot.tracker.infrastracture.web.dto.out.AthleteOutDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Tomasz Kasprzycki
 */
@RestController
@RequestMapping(value = SportTrackersApiUrl.API + "/athletes")
public class AthleteController {

    private final AthleteApplicationServiceImpl athleteApplicationServiceImpl;

    private final GraphAthleteRepository graphAthleteRepository;

    private final AthleteAssembler athleteAssembler;

    private final SecurityService securityService;

    public AthleteController(AthleteApplicationServiceImpl athleteApplicationServiceImpl,
                             GraphAthleteRepository graphAthleteRepository,
                             AthleteAssembler athleteAssembler, SecurityService securityService) {
        this.athleteApplicationServiceImpl = athleteApplicationServiceImpl;
        this.graphAthleteRepository = graphAthleteRepository;
        this.athleteAssembler = athleteAssembler;
        this.securityService = securityService;
    }

    @PostMapping
    @Secured(AuthoritiesConstants.USER)
    public void createAthlete(@RequestBody AthleteInDto athleteInDto) {
        graphAthleteRepository.save(new Athlete(athleteInDto.getName(), athleteInDto.getAthleteUuid(), athleteInDto.getFirstAndLastName()));
    }

    @GetMapping
    @Secured(AuthoritiesConstants.USER)
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
    @Secured(AuthoritiesConstants.USER)
    public List<Athlete> findAllFallowedAthletes() {
        final String currentUserLogin = SecurityUtils.getCurrentUserLogin();
        final Optional<Athlete> athleteOptional = graphAthleteRepository.findByName(currentUserLogin);
        return athleteApplicationServiceImpl.findAllFallowedAthletes(
            athleteOptional
                .orElseThrow(() -> new IllegalStateException("Athlete doesn't exist with name: " + currentUserLogin))
                .getAthleteUUID()
        );
    }

    @GetMapping("/fallowed/paged")
    @Secured(AuthoritiesConstants.USER)
    public Page<Athlete> findAllFallowedAthletesPaged(@RequestParam int page,
                                                      @RequestParam int pageSize) {
        final String currentUserLogin = SecurityUtils.getCurrentUserLogin();
        final Optional<Athlete> athleteOptional = graphAthleteRepository.findByName(currentUserLogin);
        return athleteApplicationServiceImpl.findAllFallowedAthletesPaged(
            athleteOptional
                .orElseThrow(() -> new IllegalStateException("Athlete doesn't exist with name: " + currentUserLogin))
                .getAthleteUUID(),
            PageRequest.of(page, pageSize)
        );
    }

    @PutMapping(value = "/follow")
    @Secured(AuthoritiesConstants.USER)
    public void fallow(@RequestBody FollowAthleteInDto followAthleteInDto) {
        athleteApplicationServiceImpl.follow(followAthleteInDto.getAthleteId());
    }

    @PutMapping(value = "/unfollow")
    @Secured(AuthoritiesConstants.USER)
    public void unfollow(@RequestBody FollowAthleteInDto unfallowInDto) {
        athleteApplicationServiceImpl.unfollow(unfallowInDto.getAthleteId());
    }

    @PutMapping(value = "/followed/verification")
    public List<Long> retrieveFollowedIds(@RequestBody FollowedInDto fallowInDto){
        return athleteApplicationServiceImpl.checkIfFollow(fallowInDto.getAthleteIds(), securityService.getCurrentAthlete().getId());
    }

}

