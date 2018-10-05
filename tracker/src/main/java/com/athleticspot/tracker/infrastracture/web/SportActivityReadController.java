package com.athleticspot.tracker.infrastracture.web;

import com.athleticspot.common.SecurityUtils;
import com.athleticspot.tracker.application.TimelineService;
import com.athleticspot.tracker.domain.model.GenericSportActivityRepository;
import com.athleticspot.tracker.domain.model.SportActivity;
import com.athleticspot.tracker.infrastracture.assembler.SportActivityAssemblerImpl;
import com.athleticspot.tracker.infrastracture.web.dto.out.SportActivityOutDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Tomasz Kasprzycki
 */
@RestController
@RequestMapping(value = SportTrackersApiUrl.SPORT_ACTIVITY_URL)
public class SportActivityReadController {

    private final Logger LOG = LoggerFactory.getLogger(SportActivityReadController.class);

    private final TimelineService timelineService;

    private final GenericSportActivityRepository genericSportActivityRepository;

    private final SportActivityAssemblerImpl sportActivityAssemblerImpl;

    @Autowired
    public SportActivityReadController(TimelineService timelineService,
                                       GenericSportActivityRepository genericSportActivityRepository,
                                       SportActivityAssemblerImpl sportActivityAssemblerImpl) {
        this.timelineService = timelineService;
        this.genericSportActivityRepository = genericSportActivityRepository;
        this.sportActivityAssemblerImpl = sportActivityAssemblerImpl;
    }

    @GetMapping
    public List<SportActivityOutDto> getUserSportActivities() {
        return timelineService
            .getManualSportActivities()
            .stream()
            .map(SportActivityOutDto::create)
            .sorted((o1, o2) -> o2.getDetails().getDateTime().compareTo(o1.getDetails().getDateTime()))
            .collect(Collectors.toList());
    }

    @GetMapping(value = "/paged")
    public Page<SportActivity> getUserSportActivities(
        @RequestParam(name = "page") int page,
        @RequestParam(name = "pageSize") int pageSize) {
        return sportActivityAssemblerImpl
            .pageAssemble(genericSportActivityRepository.findByUsername(SecurityUtils.getCurrentUserLogin(), new PageRequest(page, pageSize)));
    }
}
