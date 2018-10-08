package com.athleticspot.tracker.domain.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Tomasz Kasprzycki
 */
@Repository
public interface GenericSportActivityRepository extends MongoRepository<SportActivityGenericType, String> {

        Page<SportActivityGenericType> findByUsername(String username, Pageable pageable);

        List<SportActivityGenericType> findByUsername(String username, Sort sort);

}
