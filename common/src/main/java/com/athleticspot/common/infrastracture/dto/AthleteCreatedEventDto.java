package com.athleticspot.common.infrastracture.dto;

/**
 * @author Tomasz Kasprzycki
 */
public class AthleteCreatedEventDto {

    private String name;

    private String uuid;

    public AthleteCreatedEventDto(String name, String uuid) {
        this.name = name;
        this.uuid = uuid;
    }


    @Override
    public String toString() {
        return "AthleteCreatedEventDto{" +
            "name='" + name + '\'' +
            ", uuid='" + uuid + '\'' +
            '}';
    }
}
