package com.athleticspot.tracker.domain.model;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Tomasz Kasprzycki
 */
@Repository
public interface ManualSportActivityMongoRepository extends MongoRepository<ManualSportActivity, String> {
}
