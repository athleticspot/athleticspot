package com.athleticspot.tracker.infrastracture.persistance.jpa;

import com.athleticspot.tracker.domain.model.SportActivity;
import com.athleticspot.tracker.domain.model.SportActivityRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Tomasz Kasprzycki
 */
@Repository
public class SportActivityJpaRepositoryImpl implements SportActivityRepository {
    @Override
    public void store(SportActivity sportActivity) {

    }

    @Override
    public String nextSportActivityId() {
        return null;
    }

    @Override
    public void delete(String sportActivityIdentifier) {

    }
}
