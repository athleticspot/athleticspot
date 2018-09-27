package com.athleticspot.tracker.domain.model;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Tomasz Kasprzycki
 */
@Repository
public interface GeneralSportActivityRepository extends MongoRepository<StravaSportActivity, String> {
}
