package com.athleticspot.tracker.infrastracture.persistance.jpa;

import com.athleticspot.tracker.domain.model.SportActivity;
import com.athleticspot.tracker.domain.model.SportActivityRepository;
import com.athleticspot.tracker.domain.model.Timeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Tomasz Kasprzycki
 */
@Repository
public class SportActivityJpaRepositoryImpl extends SimpleJpaRepository<SportActivity, Long> implements SportActivityRepository {


    private final EntityManager em;

    @Autowired
    public SportActivityJpaRepositoryImpl(EntityManager em) {
        super(SportActivity.class, em);
        this.em = em;
    }

    @Override
    public void store(SportActivity sportActivity) {
        this.save(sportActivity);
    }

    @Override
    public String nextSportActivityId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public void delete(String sportActivityIdentifier) {

    }

    @Override
    public List<SportActivity> findByTimelineId(String timelineIdentifier) {
        Query query = em.createQuery("select e from SportActivity e " +
            "where e.timelineIdentifier = :timelineIdentifier");

        query.setParameter("timelineIdentifier", timelineIdentifier);
        return query.getResultList();

    }

    @Override
    public Optional<SportActivity> findBySportActivityId(String sportActivityId) {
        Query query = em.createQuery("select e from SportActivity e " +
            "where e.sportyActivityIdentifier = :sportyActivityIdentifier");

        query.setParameter("sportyActivityIdentifier", sportActivityId);
        List<SportActivity> list = query.getResultList();

        if (list.isEmpty()) {
            return Optional.empty();
        } else if (list.size() > 1) {
            throw new IllegalStateException("There is more than one sport activity");
        }

        return Optional.of(list.get(0));
    }
}
