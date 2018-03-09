package com.athleticspot.tracker.infrastracture.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tomasz Kasprzycki
 */
@RestController
@RequestMapping(value = ApiUrl.TRACKER_URL)
public class TrackerController {

    @GetMapping
    public String getData() {
        return "hello from tracker";
    }


}
