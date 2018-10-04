package com.athleticspot.tracker.domain.model;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Tomasz Kasprzycki
 */
@Repository
public interface GenericSportActivityRepository extends MongoRepository<SportActivityGenericType, String> {

}
