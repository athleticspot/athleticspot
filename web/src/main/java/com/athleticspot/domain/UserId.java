package com.athleticspot.domain;

import com.athleticspot.common.domain.model.AbstractId;

import java.util.UUID;

/**
 * @author Tomasz Kasprzycki
 */
public class UserId extends AbstractId {

    private static final long serialVersionUID = 1L;

    protected UserId() {
        super();
    }

    public UserId(String uuid) {
        super(uuid);
    }

    @Override
    protected void validateId(String anId) {
        try {
            UUID.fromString(anId);
        } catch (Exception e) {
            throw new IllegalArgumentException("The id has an invalid format.");
        }
    }
}
