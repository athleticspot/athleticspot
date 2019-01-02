package com.athleticspot.tracker.infrastracture.web;

import com.athleticspot.tracker.application.impl.AthleteApplicationServiceImpl;
import com.athleticspot.tracker.domain.graph.Athlete;
import com.athleticspot.tracker.infrastracture.web.dto.in.FallowInDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Tomasz Kasprzycki
 */
@RestController
@RequestMapping(value = SportTrackersApiUrl.API + "/athlete")
public class AthleteController {

    private final AthleteApplicationServiceImpl athleteApplicationServiceImpl;

    public AthleteController(AthleteApplicationServiceImpl athleteApplicationServiceImpl) {
        this.athleteApplicationServiceImpl = athleteApplicationServiceImpl;
    }

    @GetMapping("/fallowed")
    public List<Athlete> findAllFallowedAthletes(){
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

