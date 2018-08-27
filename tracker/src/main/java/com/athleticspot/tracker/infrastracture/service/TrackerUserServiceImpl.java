package com.athleticspot.tracker.infrastracture.service;

import com.athleticspot.common.SecurityUtils;
import com.athleticspot.tracker.application.TrackerUserService;
import com.athleticspot.tracker.domain.model.ApplicationUserId;
import com.athleticspot.tracker.domain.model.UserRepository;
import org.springframework.stereotype.Service;

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
        userRepository.getTimelineIdentifier(currentUserLogin);
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
