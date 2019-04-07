package com.athleticspot.tracker.infrastracture.web;

import com.athleticspot.tracker.application.impl.TrackerUserServiceImpl;
import com.athleticspot.tracker.domain.model.TrackerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tomasz Kasprzycki
 */
@RestController
@RequestMapping(SportTrackersApiUrl.TRACKER_URL)
public class SportTrackersInfoReadController {

    private final TrackerUserServiceImpl trackerUserService;

    @Autowired
    public SportTrackersInfoReadController(TrackerUserServiceImpl trackerUserService) {
        this.trackerUserService = trackerUserService;
    }

    @GetMapping(value = "/info")
    public TrackerInfo getSortTrackerInfo() {
        return trackerUserService.getTrackerInfo();
    }

}
