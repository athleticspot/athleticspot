package com.athleticspot.tracker.domain;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Tomasz Kasprzycki
 */
@Component
public class TrackerUser  {

    private String stravaUsername;

    private String athleticspotUsername;

    private String token;

    List<SportTracker> sportTrackers;

}


