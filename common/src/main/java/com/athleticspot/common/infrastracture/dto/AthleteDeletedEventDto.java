package com.athleticspot.common.infrastracture.dto;

/**
 * @author Tomasz Kasprzycki
 */
public class AthleteDeletedEventDto {

    private String login;

    public AthleteDeletedEventDto(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }
}
