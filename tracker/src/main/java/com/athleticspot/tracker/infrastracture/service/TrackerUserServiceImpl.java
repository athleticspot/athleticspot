package com.athleticspot.tracker.infrastracture.service;

import com.athleticspot.tracker.application.TrackerUserService;
import com.athleticspot.tracker.domain.model.ApplicationUserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tomasz Kasprzycki
 */
@Service
public class TrackerUserServiceImpl implements TrackerUserService {

    private final UserService userService

    @Override
    public String getTimelineIdentifier() {
        return null;
    }

    @Override
    public void addTimelineIdentifier(String user_id, String availableTimelineId) {

    }

    @Override
    public ApplicationUserId getCurrentUserId() {
        return null;
    }
}
