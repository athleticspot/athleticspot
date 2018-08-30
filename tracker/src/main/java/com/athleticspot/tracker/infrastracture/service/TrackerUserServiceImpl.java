package com.athleticspot.tracker.infrastracture.service;

import com.athleticspot.common.SecurityUtils;
import com.athleticspot.tracker.application.TrackerUserService;
import com.athleticspot.tracker.domain.model.TrackerUser;
import com.athleticspot.tracker.domain.model.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author Tomasz Kasprzycki
 */
@Service
public class TrackerUserServiceImpl implements TrackerUserService {

    private final UserRepository userRepository;

    public TrackerUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String getTimelineIdentifier() {
        final String currentUserLogin = SecurityUtils.getCurrentUserLogin();
        return userRepository.getTimelineIdentifier(currentUserLogin);
    }

    @Override
    public void addTimelineIdentifier(String timelineIdentifier) {
        Assert.notNull(timelineIdentifier, "timeline identifier cannot be null");
        final String currentUserLogin = SecurityUtils.getCurrentUserLogin();
        final TrackerUser trackerUser = userRepository.getUser(currentUserLogin);
        Assert.notNull(trackerUser, "Tracker User cannot be null");
        trackerUser.assignTimelineIdentifier(timelineIdentifier);
        userRepository.saveTrackerUser(trackerUser);
    }

}
