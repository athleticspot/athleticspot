package com.athleticspot.tracker.infrastracture.web;

import com.athleticspot.common.SecurityUtils;
import com.athleticspot.tracker.domain.graph.SportActivity;
import com.athleticspot.tracker.domain.graph.SportActivityRepository;
import com.athleticspot.tracker.infrastracture.assembler.SportActivityAssemblerImpl;
import com.athleticspot.tracker.infrastracture.web.dto.out.SportActivityOutDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tomasz Kasprzycki
 */
@RestController
@RequestMapping(value = SportTrackersApiUrl.SPORT_ACTIVITY_URL)
public class SportActivityReadController {

    private final Logger LOG = LoggerFactory.getLogger(SportActivityReadController.class);

    private final SportActivityAssemblerImpl sportActivityAssemblerImpl;

    private final SportActivityRepository sportActivityRepository;

    @Autowired
    public SportActivityReadController(SportActivityAssemblerImpl sportActivityAssemblerImpl,
                                       SportActivityRepository sportActivityRepository) {
        this.sportActivityAssemblerImpl = sportActivityAssemblerImpl;
        this.sportActivityRepository = sportActivityRepository;
    }

    @GetMapping(value = "/paged")
    public Page<SportActivityOutDto> retrieveSportActivities(@RequestParam int page,
                                                             @RequestParam int pageSize) {
        return sportActivityAssemblerImpl.pageAssemble(getActivitiesByUserId(page, pageSize));
    }

    private Page<SportActivity> getActivitiesByUserId(@RequestParam int page, @RequestParam int pageSize) {
        return sportActivityRepository.findActivitiesByUserId(
            SecurityUtils.getCurrentUserLogin(),
            PageRequest.of(page, pageSize, Sort.by(Sort.Order.desc("row.startDate")))
        );
    }

}
