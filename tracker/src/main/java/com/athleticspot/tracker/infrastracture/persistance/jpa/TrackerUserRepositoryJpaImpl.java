package com.athleticspot.tracker.infrastracture.persistance.jpa;

import com.athleticspot.tracker.domain.model.ApplicationUserId;
import com.athleticspot.tracker.domain.model.TrackerUser;
import com.athleticspot.tracker.domain.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author Tomasz Kasprzycki
 */
@Repository
public class TrackerUserRepositoryJpaImpl extends SimpleJpaRepository<TrackerUser, Long>
    implements UserRepository {

    private final EntityManager em;

    @Autowired
    public TrackerUserRepositoryJpaImpl(EntityManager em) {
        super(TrackerUser.class, em);
        this.em = em;
    }

    @Override
    public ApplicationUserId getCurrentUserId() {

        return null;
    }

    @Override
    public String getTimelineIdentifier(String currentUserLogin) {
        return null;
    }

    @Override
    public void addTimelineIdentifier(String user_id, String availableTimelineId) {

    }

    @Override
    public List<TrackerUser> findAllUsers() {
        return this.findAll();
    }
}
