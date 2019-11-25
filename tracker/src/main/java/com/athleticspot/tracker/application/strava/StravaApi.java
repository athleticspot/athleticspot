package com.athleticspot.tracker.application.strava;

import com.google.common.collect.Lists;
import javastrava.model.StravaActivity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Tomasz Kasprzycki
 */
@Component
public class StravaApi {

    public List<StravaActivity> getSportActivities(int firstPage, int pageSize, LocalDateTime activitiesAfter) {


        return Lists.newArrayList();
    }
}
