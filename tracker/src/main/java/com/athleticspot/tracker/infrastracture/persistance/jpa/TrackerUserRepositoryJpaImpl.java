package com.athleticspot.tracker.infrastracture.persistance.jpa;

import com.athleticspot.tracker.domain.model.TrackerUser;
import com.athleticspot.tracker.domain.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

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
    public List<TrackerUser> findAllUsers() {
        return this.findAll();
    }

    @Override
    public void saveTrackerUser(TrackerUser trackerUser) {
        this.save(trackerUser);
    }

    @Override
    public TrackerUser getUser(String userLogin) {
        return this.getTrackerUser(userLogin);
    }

    @Override
    public TrackerUser save(TrackerUser trackerUser) {
        return super.save(trackerUser);
    }

    @Override
    public List<TrackerUser> getStravaUsers() {
        final String queryString = "SELECT e FROM TrackerUser e" +
            " WHERE e.stravaCode IS NOT NULL ";

        Query query = em.createQuery(queryString, TrackerUser.class);
        return query.getResultList();
    }

    @Override
    public Optional<TrackerUser> findByLogin(String login) {
        return Optional.ofNullable(getTrackerUser(login));
    }

    private TrackerUser getTrackerUser(String userLogin) {
        final String queryString = "SELECT e FROM TrackerUser e" +
            " WHERE e.login = :userLogin";

        Query query = em.createQuery(queryString, TrackerUser.class);

        query.setParameter("userLogin", userLogin);
        final List<TrackerUser> resultList = query.getResultList();

        if (resultList.isEmpty()) {
            return null;
        } else if (resultList.size() > 1) {
            throw new IllegalStateException("There is more than one timeline for user login: " + userLogin);
        }
        return resultList.get(0);
    }
}
