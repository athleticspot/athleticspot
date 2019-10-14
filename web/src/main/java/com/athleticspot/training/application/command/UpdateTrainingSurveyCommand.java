package com.athleticspot.training.application.command;

import com.athleticspot.training.domain.trainingsurvey.HealthInformation;
import com.athleticspot.training.domain.trainingsurvey.NutritionInformation;
import com.athleticspot.training.infrastracture.dto.in.BaseInformationInDto;

/**
 * @author Tomasz Kasprzycki
 */
public class UpdateTrainingSurveyCommand {

    private final BaseInformationInDto baseInformation;
    private final HealthInformation healthInformation;
    private final NutritionInformation nutritionInformation;

    private UpdateTrainingSurveyCommand(BaseInformationInDto baseInformation,
                                        HealthInformation healthInformation,
                                        NutritionInformation nutritionInformation) {

        this.baseInformation = baseInformation;
        this.healthInformation = healthInformation;
        this.nutritionInformation = nutritionInformation;
    }

    public static UpdateTrainingSurveyCommand create(BaseInformationInDto baseInformation,
                                                     HealthInformation healthInformation,
                                                     NutritionInformation nutritionInformation) {
        return new UpdateTrainingSurveyCommand(baseInformation, healthInformation, nutritionInformation);
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
}
