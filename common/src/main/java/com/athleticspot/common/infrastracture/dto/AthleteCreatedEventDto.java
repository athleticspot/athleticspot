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

    public String getName() {
        return name;
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public String toString() {
        return "AthleteCreatedEventDto{" +
            "name='" + name + '\'' +
            ", uuid='" + uuid + '\'' +
            '}';
    }
}
