package com.athleticspot.tracker.infrastracture.web;

import com.athleticspot.tracker.application.TrackerActions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tomasz Kasprzycki
 */
@RestController
@RequestMapping(value = SportTrackersApiUrl.TRACKER_URL)
public class TrackerController {

    private final TrackerActions trackerActions;

    @Autowired
    public TrackerController(TrackerActions trackerActions) {
        this.trackerActions = trackerActions;
    }

    @GetMapping
    public String getActivities() {
         trackerActions.fetchActivities();
         return "test";
    }
}
