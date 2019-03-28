package com.athleticspot.tracker.infrastracture.service;

import com.athleticspot.common.SecurityUtils;
import com.athleticspot.tracker.application.TrackerUserService;
import com.athleticspot.tracker.domain.model.TrackerUser;
import com.athleticspot.tracker.domain.model.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;

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
    public void addTimelineIdentifier(String timelineIdentifier) {
        Assert.notNull(timelineIdentifier, "timeline identifier cannot be null");
        final String currentUserLogin = SecurityUtils.getCurrentUserLogin();
        final TrackerUser trackerUser = userRepository.getUser(currentUserLogin);
        Assert.notNull(trackerUser, "Tracker User cannot be null");
        userRepository.saveTrackerUser(trackerUser);
    }

    @Override
    @Transactional
    public void addStravaCode(String stravaCode, String username) {
        Assert.notNull(stravaCode, "strava code cannot be null");
        Assert.notNull(username, "username cannot be null");

        final TrackerUser user = userRepository.getUser(username);
        user.assignStravaCode(stravaCode);
        userRepository.saveTrackerUser(user);
    }

    @Override
    public String getStravaCode(String username) {
        return userRepository.getUser(username).getStravaCode();
    }

    @Override
    @Transactional
    public void assignStravaLastSynchronizationDate(LocalDateTime stravaLastSynchronizationDate, String username) {
        userRepository.save(userRepository
            .getUser(username)
            .assignStravaLastSynchronizationDate(stravaLastSynchronizationDate));
    }

    @Override
    public LocalDateTime getStravaLastSynchronizationDate(String username) {
        return userRepository.getUser(username).getStravaLastSynchronizationDate();
    }

    @Override
    public List<TrackerUser> getStravaUsers(){
        return userRepository.getStravaUsers();
    }




}
