package com.athleticspot.tracker.infrastracture.web;

import com.athleticspot.tracker.application.TimelineService;
import com.athleticspot.tracker.domain.model.TrackerSource;
import com.athleticspot.tracker.infrastracture.web.assembler.ManualSportActivityDetailsInDtoAssembler;
import com.athleticspot.tracker.infrastracture.web.dto.in.SportActivityInDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Tomasz Kasprzycki
 */
@RestController
@RequestMapping(value = SportTrackersApiUrl.SPORT_ACTIVITY_URL)
public class SportActivityWriteController {

    private final Logger LOG = LoggerFactory.getLogger(SportActivityWriteController.class);

    private final TimelineService timelineService;

    @Autowired
    public SportActivityWriteController(TimelineService timelineService) {
        this.timelineService = timelineService;
    }

    @PostMapping
    public void createSportActivity(@RequestBody @Valid SportActivityInDto sportActivityInDto) {
        LOG.info("Incoming request: {}", sportActivityInDto);
        timelineService.addActivity(
            ManualSportActivityDetailsInDtoAssembler.assemble(sportActivityInDto),
            TrackerSource.MANUAL.getSource(),
            sportActivityInDto.getStartDate()
        );
    }

    @PutMapping
    public void updateSportActivity() {

    }

    @DeleteMapping
    public void deleteSportActivity() {

    }
}
