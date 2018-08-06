package com.athleticspot.tracker.infrastracture.persistance.jpa;

import com.athleticspot.tracker.domain.model.ApplicationUserId;
import com.athleticspot.tracker.domain.model.UserRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Tomasz Kasprzycki
 */
@Repository
public class UserRepositoryJpaImpl implements UserRepository {
    @Override
    public ApplicationUserId getCurrentUserId() {
        return null;
    }

    @Override
    public String getTimelineIdentifier() {
        return null;
    }
}
