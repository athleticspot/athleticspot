package com.athleticspot.tracker.domain.graph;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author Tomasz Kasprzycki
 */
public interface SportActivityRepository extends CrudRepository<SportActivity, Long> {

    @Query("Match (a:Athlete{name:\"Tomasz Kasprzycki\"})-[r:FALLOW]->(n:Athlete)-[g:PERFORM]->(h:SportActivity) with collect(h) as rows " +
        "match (t:Athlete{name:\"Tomasz Kasprzycki\"})-[:PERFORM]->(z:SportActivity) with rows + collect(z) as allRows " +
        "UNWIND allRows as row " +
        "return row")
    Iterable<SportActivity> findActivitiesByUserId(Long userId);


    @Query(value = "Match (a:Athlete{name:{name}})-[r:FALLOW]->(n:Athlete)-[g:PERFORM]->(h:SportActivity) with collect(h) as rows " +
        "match (t:Athlete{name:{name}})-[:PERFORM]->(z:SportActivity) with rows + collect(z) as allRows " +
        "UNWIND allRows as row " +
        "return row",
        countQuery = "Match (a:Athlete{name:{name}})-[r:FALLOW]->(n:Athlete)-[g:PERFORM]->(h:SportActivity) with collect(h) as rows " +
            "match (t:Athlete{name:{name}})-[:PERFORM]->(z:SportActivity) with rows + collect(z) as allRows " +
            "UNWIND allRows as row " +
            "return count(row)")
    Page<SportActivity> findActivitiesByUserId(@Param("name") String name, Pageable pageable);

    @Query(value = "MATCH (a:SportActivity) where id(a)={sportActivityId} DETACH DELETE a ")
    void detachDeleteSportActivity(Long sportActivityId);


}
