package com.athleticspot.common.infrastracture.dto;

/**
 * @author Tomasz Kasprzycki
 */
public class AthleteUpdatedEventDto {


    private final String name;
    private final String uuid;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String langKey;
    private final String imageUrl;

    private AthleteUpdatedEventDto(String name, String uuid, String firstName, String lastName, String email, String langKey, String imageUrl) {
        this.name = name;
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.langKey = langKey;
        this.imageUrl = imageUrl;
    }

    public static AthleteUpdatedEventDto create(String name, String uuid, String firstName, String lastName, String email, String langKey, String imageUrl) {
        return new AthleteUpdatedEventDto(name, uuid, firstName, lastName, email, langKey, imageUrl);
    }

    public String getName() {
        return name;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getLangKey() {
        return langKey;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
