package com.athleticspot.common.infrastracture.dto;

/**
 * @author Tomasz Kasprzycki
 */
public class UserCreatedEventDto {

    private String name;

    private String uuid;

    private final String firstAndLastName;

    public UserCreatedEventDto(String name, String uuid, String firstAndLastName) {
        this.name = name;
        this.uuid = uuid;
        this.firstAndLastName = firstAndLastName;
    }

    public String getName() {
        return name;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFirstAndLastName() {
        return firstAndLastName;
    }

    @Override
    public String toString() {
        return "UserCreatedEventDto{" +
            "name='" + name + '\'' +
            ", uuid='" + uuid + '\'' +
            ", firstAndLastName='" + firstAndLastName + '\'' +
            '}';
    }
}
