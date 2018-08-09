package com.athleticspot.tracker.infrastracture.persistance.jpa;

import com.athleticspot.tracker.domain.model.Timeline;
import com.athleticspot.tracker.domain.model.TimelineRepository;
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
public class TimelineRepositoryJpaImpl extends SimpleJpaRepository<Timeline, Long> implements TimelineRepository {

    private final EntityManager em;

    @Autowired
    public TimelineRepositoryJpaImpl(EntityManager em) {
        super(Timeline.class, em);
        this.em = em;
    }

    @Override
    public String nextTimelineId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public void store(Timeline timeline) {
        this.save(timeline);
    }

    @Override
    public Optional<Timeline> find(String timelineIdentifier) {
        Query query = em.createQuery("select e from Timeline e where e.timelineIdentifier = :timelineIdentifier");

        query.setParameter("timelineIdentifier", timelineIdentifier);
        List<Timeline> list = (List<Timeline>) query.getResultList();

        if (list.isEmpty()) {
            return Optional.empty();
        } else if (list.size() > 1) {
            throw new IllegalStateException("There is more than one timeline");
        }

        return Optional.of(list.get(0));
    }
}
