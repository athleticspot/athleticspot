package com.athleticspot.tracker.infrastracture.web;

import com.athleticspot.tracker.domain.model.Timeline;
import com.athleticspot.tracker.infrastracture.web.dto.out.TimelineOutDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tomasz Kasprzycki
 */
@RestController
@RequestMapping(value = SportTrackersApiUrl.TIMELINE_URL)
public class TimelineController {

    @GetMapping
    public TimelineOutDto getTimeline(){
        return null;
    }
}
