package com.athleticspot.tracker.domain.model;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Transient;

/**
 * @author Tomasz Kasprzycki
 */
@Document(collection = "sport_activities")
public class SportActivityGenericType {

    @Transient
    protected String username;

}
