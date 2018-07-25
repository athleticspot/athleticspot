package com.athleticspot.training.infrastracture.dto.out;

import com.athleticspot.training.domain.trainingsurvey.BaseInformation;
import com.athleticspot.training.domain.trainingsurvey.HealthInformation;
import com.athleticspot.training.domain.trainingsurvey.NutritionInformation;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

/**
 * @author Tomasz Kasprzycki
 */
@JsonDeserialize
@JsonSerialize
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class TrainingSurveyOutDto implements Serializable {

    private String trainingSurveyUuid;
    private BaseInformation baseInformation;
    private HealthInformation healthInformation;
    private NutritionInformation nutritionInformation;

    public TrainingSurveyOutDto trainingSurveyUuid(final String trainingSurveyUuid) {
        this.trainingSurveyUuid = trainingSurveyUuid;
        return this;
    }

    public TrainingSurveyOutDto baseInformation(final BaseInformation baseInformation) {
        this.baseInformation = baseInformation;
        return this;
    }

    public TrainingSurveyOutDto healthInformation(final HealthInformation healthInformation) {
        this.healthInformation = healthInformation;
        return this;
    }

    public TrainingSurveyOutDto nutritionInformation(final NutritionInformation nutritionInformation) {
        this.nutritionInformation = nutritionInformation;
        return this;
    }

}
