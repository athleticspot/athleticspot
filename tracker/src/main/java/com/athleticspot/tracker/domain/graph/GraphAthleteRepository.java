package com.athleticspot.tracker.domain.graph;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Tomasz Kasprzycki
 */
@Repository
public interface GraphAthleteRepository extends Neo4jRepository<Athlete, Long> {

    @Query("Match (a:Athlete{athleteUUID:{athleteUuid}})-[:FALLOW]->(b:Athlete) return b")
    List<Athlete> findAllFallowedAthletes(@Param("athleteUuid") String athleteUuid);

    @Query(value = "Match (a:Athlete{athleteUUID:{athleteUuid}})-[:FALLOW]->(b:Athlete) return b",
    countQuery = "Match (a:Athlete{athleteUUID:{athleteUuid}})-[:FALLOW]->(b:Athlete) return count(b)")
    Page<Athlete> findAllFallowedAthletesPaged(@Param("athleteUuid") String athleteUuid, Pageable pageRequest);

    @Query(value = "Match (n:Athlete) where toLower(n.firstAndLastName) contains toLower({firstAndLastName}) return n",
        countQuery = "Match (n:Athlete) where toLower(n.firstAndLastName) contains toLower({firstAndLastName}) return count(*)")
    Page<Athlete> findAthletes(@Param("firstAndLastName") String firstAndLastName, Pageable pageRequest);

    @Query(value = "Match (n:Athlete) where n.name <> {me} and toLower(n.firstAndLastName) contains toLower({firstAndLastName}) return n",
        countQuery = "Match (n:Athlete) where n.name <> {me} and toLower(n.firstAndLastName) contains toLower({firstAndLastName}) return count(*)")
    Page<Athlete> findAthletesWithoutMe(@Param("firstAndLastName") String firstAndLastName, @Param("me") String me, Pageable pageRequest);


    @Query("Match (a:Athlete{name:{name}})-[r:PERFORM]-(s:SportActivity) Delete a,r,s ")
    void deleteAthlete(@Param("name") String name);

    Optional<Athlete> findByName(String name);
}
