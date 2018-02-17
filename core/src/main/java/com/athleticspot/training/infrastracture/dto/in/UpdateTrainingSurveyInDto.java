package com.athleticspot.training.infrastracture.dto.in;

import com.athleticspot.training.domain.trainingsurvey.BaseInformation;
import com.athleticspot.training.domain.trainingsurvey.HealthInformation;
import com.athleticspot.training.domain.trainingsurvey.NutritionInformation;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Tomasz Kasprzycki
 */
@JsonDeserialize()
@JsonAutoDetect(
    fieldVisibility = Visibility.ANY,
    getterVisibility = Visibility.NONE,
    setterVisibility = Visibility.NONE)
public class UpdateTrainingSurveyInDto implements Serializable {

    @NotNull
    private String trainingSurveyUuid;

    @NotNull
    private BaseInformation baseInformation;

    @NotNull
    private HealthInformation healthInformation;

    @NotNull
    private NutritionInformation nutritionInformation;

    public String getTrainingSurveyUuid() {
        return trainingSurveyUuid;
    }

    public BaseInformation getBaseInformation() {
        return baseInformation;
    }

    public HealthInformation getHealthInformation() {
        return healthInformation;
    }

    public NutritionInformation getNutritionInformation() {
        return nutritionInformation;
    }
}
