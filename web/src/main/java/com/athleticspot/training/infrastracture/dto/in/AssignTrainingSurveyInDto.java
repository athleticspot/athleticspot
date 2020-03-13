package com.athleticspot.training.infrastracture.dto.in;

import com.athleticspot.common.domain.model.MetricSystemType;
import com.athleticspot.training.domain.trainingsurvey.HealthInformation;
import com.athleticspot.training.domain.trainingsurvey.NutritionInformation;
import com.athleticspot.training.domain.trainingsurvey.TrainingGoal;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Tomasz Kasprzycki
 */
@JsonDeserialize(builder = AssignTrainingSurveyInDto.AssignTrainingSurveyInDtoBuilder.class)
@JsonAutoDetect(
    fieldVisibility = Visibility.ANY,
    getterVisibility = Visibility.NONE,
    setterVisibility = Visibility.NONE)
public class AssignTrainingSurveyInDto implements Serializable {

    @NotNull
    private BaseInformationInDto baseInformation;

    @NotNull
    private HealthInformation healthInformation;

    @NotNull
    private NutritionInformation nutritionInformation;

    private AssignTrainingSurveyInDto(BaseInformationInDto baseInformation,
                                      HealthInformation healthInformation,
                                      NutritionInformation nutritionInformation) {
        this.baseInformation = baseInformation;
        this.healthInformation = healthInformation;
        this.nutritionInformation = nutritionInformation;
    }

    public BaseInformationInDto getBaseInformation() {
        return baseInformation;
    }

    public HealthInformation getHealthInformation() {
        return healthInformation;
    }

    public NutritionInformation getNutritionInformation() {
        return nutritionInformation;
    }

    @Override
    public String toString() {
        return "AssignTrainingSurveyInDto{" +
            "baseInformation=" + baseInformation +
            ", healthInformation=" + healthInformation +
            ", nutritionInformation=" + nutritionInformation +
            '}';
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class AssignTrainingSurveyInDtoBuilder {

        private BaseInformationInDto baseInformation;
        private HealthInformation healthInformation;
        private NutritionInformation nutritionInformation;

        public AssignTrainingSurveyInDtoBuilder setBaseInformation(final BaseInformationInDto baseInformation) {
            this.baseInformation = baseInformation;
            return this;
        }

        public AssignTrainingSurveyInDtoBuilder setHealthInformation(final HealthInformation healthInformation) {
            this.healthInformation = healthInformation;
            return this;
        }

        public AssignTrainingSurveyInDtoBuilder setNutritionInformation(final NutritionInformation nutritionInformation) {
            this.nutritionInformation = nutritionInformation;
            return this;
        }

        public AssignTrainingSurveyInDtoBuilder setTrainingGoal(final TrainingGoal trainingGoal) {
            return this;
        }

        public AssignTrainingSurveyInDtoBuilder setMetricSystemType(final MetricSystemType metricSystemType) {
            return this;
        }


        public AssignTrainingSurveyInDto build() {
            return new AssignTrainingSurveyInDto(
                this.baseInformation,
                this.healthInformation,
                this.nutritionInformation
            );
        }
    }
}
