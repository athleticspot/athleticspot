package com.athleticspot.tracker.application.impl;

import com.athleticspot.common.SecurityUtils;
import com.athleticspot.tracker.application.TrackerUserService;
import com.athleticspot.tracker.domain.model.TrackerInfo;
import com.athleticspot.tracker.domain.model.TrackerUser;
import com.athleticspot.tracker.domain.model.TrackerUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * @author Tomasz Kasprzycki
 */
@Service
public class TrackerUserServiceImpl implements TrackerUserService {

    private final TrackerUserRepository trackerUserRepository;

    public TrackerUserServiceImpl(TrackerUserRepository trackerUserRepository) {
        this.trackerUserRepository = trackerUserRepository;
    }

    @Override
    @Transactional
    public void addStravaCode(String stravaCode, String username) {
        Assert.notNull(stravaCode, "strava code cannot be null");
        Assert.notNull(username, "username cannot be null");

        final TrackerUser user = trackerUserRepository.getUser(username);
        user.assignStravaCode(stravaCode);
        trackerUserRepository.saveTrackerUser(user);
    }

    @Override
    public String getStravaCode(String username) {
        return trackerUserRepository.getUser(username).getStravaCode();
    }

    @Override
    @Transactional
    public void assignStravaLastSynchronizationDate(LocalDateTime stravaLastSynchronizationDate, String username) {
        trackerUserRepository.save(trackerUserRepository
            .getUser(username)
            .assignStravaLastSynchronizationDate(stravaLastSynchronizationDate));
    }

    @Override
    public LocalDateTime getStravaLastSynchronizationDate(String username) {
        return trackerUserRepository.getUser(username).getStravaLastSynchronizationDate();
    }

    @Override
    public List<TrackerUser> getStravaUsers(){
        return trackerUserRepository.getStravaUsers();
    }

    @Override
    public TrackerInfo getTrackerInfo(){
        final TrackerUser user = trackerUserRepository.getUser(SecurityUtils.getCurrentUserLogin());
        boolean connectedToStrava = Objects.nonNull(user.getStravaCode());
        return new TrackerInfo(connectedToStrava);
    }



}
