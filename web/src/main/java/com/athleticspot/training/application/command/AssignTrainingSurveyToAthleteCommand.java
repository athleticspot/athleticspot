package com.athleticspot.training.application.command;

import com.athleticspot.common.command.ResponseAwareDomainCommand;
import com.athleticspot.common.domain.model.MetricSystemType;
import com.athleticspot.training.domain.trainingsurvey.BaseInformation;
import com.athleticspot.training.domain.trainingsurvey.HealthInformation;
import com.athleticspot.training.domain.trainingsurvey.NutritionInformation;

import java.time.LocalDate;


/**
 * @author Tomasz Kasprzycki
 */
public class AssignTrainingSurveyToAthleteCommand
    extends ResponseAwareDomainCommand<String> {

    private final BaseInformation baseInformation;
    private final HealthInformation healthInformation;
    private final NutritionInformation nutritionInformation;

    private AssignTrainingSurveyToAthleteCommand(LocalDate birthday,
                                                 Double weight,
                                                 Double height,
                                                 MetricSystemType metricSystemType,
                                                 boolean healthContraindications,
                                                 boolean stressTest,
                                                 boolean bloodTest,
                                                 Double hoursOfSleep,
                                                 boolean meatAcceptance,
                                                 boolean dairiesAcceptance,
                                                 boolean allergies,
                                                 boolean foodIntolerance) {
        this.healthInformation = new HealthInformation(
            healthContraindications,
            stressTest,
            bloodTest,
            hoursOfSleep);

        this.baseInformation = new BaseInformation(
            birthday,
            weight,
            height
        );

        nutritionInformation = new NutritionInformation(meatAcceptance,
            dairiesAcceptance,
            allergies,
            foodIntolerance);
    }

    public static AssignTrainingSurveyToAthleteCommand of(LocalDate birthday,
                                                          Double weight,
                                                          Double height,
                                                          boolean healthContraindications,
                                                          boolean stressTest,
                                                          boolean bloodTest,
                                                          Double hoursOfSleep,
                                                          boolean meatAcceptance,
                                                          boolean dairiesAcceptance,
                                                          boolean allergies,
                                                          boolean foodIntolerance) {
        return new AssignTrainingSurveyToAthleteCommand(
            birthday,
            weight,
            height,
            null,
            healthContraindications,
            stressTest,
            bloodTest,
            hoursOfSleep,
            meatAcceptance,
            dairiesAcceptance,
            allergies, foodIntolerance);
    }

    public BaseInformation getBaseInformation() {
        return this.baseInformation;
    }

    public HealthInformation getHealthInformation() {
        return healthInformation;
    }

    public NutritionInformation getNutritionInformation() {
        return nutritionInformation;
    }

}
