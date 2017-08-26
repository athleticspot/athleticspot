package com.athleticspot.training.infrastracture.dto.in;

import com.athleticspot.training.domain.MeasureSystemType;
import com.athleticspot.training.domain.trainingsurvey.BaseInformation;
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
    private BaseInformation baseInformation;

    @NotNull
    private HealthInformation healthInformation;

    @NotNull
    private NutritionInformation nutritionInformation;

    @NotNull
    private TrainingGoal trainingGoal;

    @NotNull
    private MeasureSystemType measureSystemType;

    private AssignTrainingSurveyInDto(
        BaseInformation baseInformation,
        HealthInformation healthInformation,
        NutritionInformation nutritionInformation,
        TrainingGoal trainingGoal,
        MeasureSystemType measureSystemType) {
        this.baseInformation = baseInformation;
        this.healthInformation = healthInformation;
        this.nutritionInformation = nutritionInformation;
        this.trainingGoal = trainingGoal;
        this.measureSystemType = measureSystemType;
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

    public TrainingGoal getTrainingGoal() {
        return trainingGoal;
    }

    public MeasureSystemType getMeasureSystemType() {
        return measureSystemType;
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class AssignTrainingSurveyInDtoBuilder {

        private BaseInformation baseInformation;
        private HealthInformation healthInformation;
        private NutritionInformation nutritionInformation;
        private TrainingGoal trainingGoal;
        private MeasureSystemType measureSystemType;


        public AssignTrainingSurveyInDtoBuilder setBaseInformation(final BaseInformation baseInformation) {
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
            this.trainingGoal = trainingGoal;
            return this;
        }

        public AssignTrainingSurveyInDtoBuilder setMeasureSystemType(final MeasureSystemType measureSystemType) {
            this.measureSystemType = measureSystemType;
            return this;
        }


        public AssignTrainingSurveyInDto build() {
            return new AssignTrainingSurveyInDto(
                this.baseInformation,
                this.healthInformation,
                this.nutritionInformation,
                this.trainingGoal,
                this.measureSystemType
            );
        }

    }
}