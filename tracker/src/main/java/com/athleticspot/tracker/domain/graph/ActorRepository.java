package com.athleticspot.tracker.domain.graph;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Tomasz Kasprzycki
 */
@Repository
public interface ActorRepository extends CrudRepository<Actor, Long> {


}
