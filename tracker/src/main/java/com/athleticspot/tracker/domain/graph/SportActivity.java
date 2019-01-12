package com.athleticspot.tracker.domain.graph;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.typeconversion.DateLong;

import java.util.Date;

/**
 * @author Tomasz Kasprzycki
 */
@NodeEntity
public class SportActivity {

    @Id
    @GeneratedValue
    private Long id;

    @DateLong
    private Date date;

    private String type;

    private SportActivity() {
        //neo4j purpose
    }

    private SportActivity(Date date, String type) {
        this.date = date;
        this.type = type;
    }

    public static SportActivity create(Date date, String type) {
        return new SportActivity(date, type);
    }

    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getType() {
        return type;
    }
}
