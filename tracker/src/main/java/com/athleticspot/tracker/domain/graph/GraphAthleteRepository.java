package com.athleticspot.tracker.domain.graph;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Tomasz Kasprzycki
 */
@Repository
public interface GraphAthleteRepository extends Neo4jRepository<Athlete, Long> {

    @Query("Match (a:Athlete)-[:FALLOW]->(b:Athlete) return b")
    List<Athlete> findAllFallowedAthletes(String athleteUuid);

    @Query(value = "Match (n:Athlete) where toLower(n.name) contains toLower({name}) return n",
    countQuery = "Match (n:Athlete) where toLower(n.name) contains toLower({name}) return count(*)")
    Page<Athlete> findAthletes(@Param("name") String name, Pageable pageRequest);
}
