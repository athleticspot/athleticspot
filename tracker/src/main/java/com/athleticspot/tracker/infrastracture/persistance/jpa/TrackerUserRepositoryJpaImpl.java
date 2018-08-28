package com.athleticspot.tracker.infrastracture.persistance.jpa;

import com.athleticspot.tracker.domain.model.ApplicationUserId;
import com.athleticspot.tracker.domain.model.TrackerUser;
import com.athleticspot.tracker.domain.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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
    public String getTimelineIdentifier(String userLogin) {
        final String queryString = "select e from TrackerUser e" +
            " where e.login = :userLogin";

        Query query = em.createQuery(queryString, TrackerUser.class);

        query.setParameter("userLogin", userLogin);
        final List<TrackerUser> resultList = query.getResultList();

        if (resultList.isEmpty()) {
            return null;
        } else if (resultList.size() > 1) {
            throw new IllegalStateException("There is more than one timeline");
        }
        return resultList.get(0).getTimelineIdentifier();
    }

    @Override
    public void addTimelineIdentifier(String userLogin, String timelineIdentifier) {

    }

    @Override
    public List<TrackerUser> findAllUsers() {
        return this.findAll();
    }
}
