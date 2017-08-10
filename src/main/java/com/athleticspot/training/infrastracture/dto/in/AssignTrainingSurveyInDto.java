package com.athleticspot.training.infrastracture.dto.in;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;

/**
 * @author Tomasz Kasprzycki
 */
@JsonDeserialize
@JsonAutoDetect(
    fieldVisibility = Visibility.ANY,
    getterVisibility = Visibility.NONE,
    setterVisibility = Visibility.NONE)
public class AssignTrainingSurveyInDto implements Serializable {

    private String uuid;
}
