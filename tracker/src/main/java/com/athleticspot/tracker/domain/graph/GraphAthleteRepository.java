package com.athleticspot.tracker.domain.graph;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Tomasz Kasprzycki
 */
@Repository
public interface GraphAthleteRepository extends CrudRepository<Athlete, Long> {

    @Query("Match (a:Athlete)-[:FALLOW]->(b:Athlete) return b")
    List<Athlete> findAllFallowedAthletes(String athleteUuid);

    Page<Athlete> findByNameIgnoreCase(String name, PageRequest pageRequest);
}
