package com.athleticspot.training.application.command;

import com.athleticspot.common.command.ResponseAwareDomainCommand;
import com.athleticspot.training.domain.MetricSystemType;
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
    private final MetricSystemType metricSystemType;

    public AssignTrainingSurveyToAthleteCommand(LocalDate birthday,
                                                Double weight,
                                                Double height,
                                                boolean healthContraindications,
                                                boolean stressTest,
                                                boolean bloodTest,
                                                Double hoursOfSleep,
                                                boolean meatAcceptance,
                                                boolean dairiesAcceptance,
                                                boolean allergies,
                                                boolean foodIntolerance,
                                                MetricSystemType metricSystemType) {
        this.metricSystemType = metricSystemType;
        this.healthInformation = new HealthInformation(
            healthContraindications,
            stressTest,
            bloodTest,
            hoursOfSleep);
        this.baseInformation = new BaseInformation(
            birthday,
            weight,
            height);
        nutritionInformation = new NutritionInformation(meatAcceptance,
            dairiesAcceptance,
            allergies,
            foodIntolerance);
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

    public MetricSystemType getMetricSystemType() {
        return metricSystemType;
    }
}
