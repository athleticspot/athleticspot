package com.athleticspot.tracker.domain.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Tomasz Kasprzycki
 */
@Document(collection = "sport_activities")
public class SportActivityGenericType {

    protected String username;

    public String getUsername() {
        return username;
    }
}
