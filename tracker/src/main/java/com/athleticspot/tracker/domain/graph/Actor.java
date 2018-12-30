package com.athleticspot.tracker.domain.graph;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * @author Tomasz Kasprzycki
 */
@NodeEntity
public class Actor {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Actor() {
        // Empty constructor required as of Neo4j API 2.0.5
    };

    public Actor(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
