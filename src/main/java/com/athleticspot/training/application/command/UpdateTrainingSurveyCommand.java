package com.athleticspot.training.application.command;

import com.athleticspot.training.domain.trainingsurvey.BaseInformation;
import com.athleticspot.training.domain.trainingsurvey.HealthInformation;
import com.athleticspot.training.domain.trainingsurvey.NutritionInformation;

/**
 * @author Tomasz Kasprzycki
 */
public class UpdateTrainingSurveyCommand {

    private final BaseInformation baseInformation;
    private final HealthInformation healthInformation;
    private final NutritionInformation nutritionInformation;

    private UpdateTrainingSurveyCommand(BaseInformation baseInformation,
                                        HealthInformation healthInformation,
                                        NutritionInformation nutritionInformation) {

        this.baseInformation = baseInformation;
        this.healthInformation = healthInformation;
        this.nutritionInformation = nutritionInformation;
    }

    public static UpdateTrainingSurveyCommand create(BaseInformation baseInformation,
                                                     HealthInformation healthInformation,
                                                     NutritionInformation nutritionInformation) {
        return new UpdateTrainingSurveyCommand(baseInformation, healthInformation, nutritionInformation);
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
