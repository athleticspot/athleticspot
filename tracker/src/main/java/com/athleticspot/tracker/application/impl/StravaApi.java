package com.athleticspot.tracker.application.impl;

import com.google.common.collect.Lists;
import javastrava.model.StravaActivity;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Tomasz Kasprzycki
 */
public class StravaApi {

    public List<StravaActivity> getSportActivities(int firstPage, int pageSize, LocalDateTime activitiesAfter) {
        return Lists.newArrayList();
    }
}
