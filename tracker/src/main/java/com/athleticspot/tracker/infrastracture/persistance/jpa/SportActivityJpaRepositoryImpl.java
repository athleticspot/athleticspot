package com.athleticspot.tracker.infrastracture.persistance.jpa;

import com.athleticspot.tracker.domain.model.SportActivity;
import com.athleticspot.tracker.domain.model.SportActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
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
    public SportActivity findByTimelineId(String timelineIdentifier) {
        return null;
    }
}
