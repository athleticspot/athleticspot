package com.athleticspot.tracker.infrastracture.web;

import com.athleticspot.tracker.application.TimelineService;
import com.athleticspot.tracker.domain.model.TrackerSource;
import com.athleticspot.tracker.infrastracture.web.dto.in.SportActivityInDto;
import com.athleticspot.tracker.infrastracture.web.dto.out.SportActivityOutDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Tomasz Kasprzycki
 */
@RestController
@RequestMapping(value = SportTrackersApiUrl.SPORT_ACTIVITY_URL)
public class SportActivityController {

    private final Logger LOG = LoggerFactory.getLogger(SportActivityController.class);

    private final TimelineService timelineService;

    @Autowired
    public SportActivityController(TimelineService timelineService) {
        this.timelineService = timelineService;
    }

    @GetMapping
    public List<SportActivityOutDto> getUserSportActivities(){
        return timelineService
            .getSportActivities()
            .stream()
            .map(SportActivityOutDto::create)
            .sorted((o1, o2) -> o2.getDetails().getDateTime().compareTo(o1.getDetails().getDateTime()))
            .collect(Collectors.toList());
    }

    @PostMapping
    public void createSportActivity(@RequestBody @Valid SportActivityInDto sportActivityInDto){
        LOG.info("Incoming request: {}", sportActivityInDto);
        timelineService.addActivity(SportActivityDtoAssembler.assemble(sportActivityInDto), TrackerSource.MANUAL.getSource());
    }

    @PutMapping
    public void updateSportActivity(){

    }

    @DeleteMapping
    public void deleteSportActivity(){

    }
}
