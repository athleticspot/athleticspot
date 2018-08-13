package com.athleticspot.training.domain.trainingsurvey;

import com.athleticspot.common.domain.model.IdentifiedDomainObject;
import com.athleticspot.training.domain.AthleteId;
import com.athleticspot.training.domain.TrainingIntensity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.geo.Distance;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.Duration;

/**
 * @author Tomasz Kasprzycki
 */
@Entity
@Table(name = "training_survey")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "training_survey_seq", allocationSize = 1)
public class TrainingSurvey extends IdentifiedDomainObject {

    private static final long serialVersionUID = 1L;

    @Embedded
    @AttributeOverride(name = "uuid", column = @Column(name = "survey_uuid", nullable = false))
    private TrainingSurveyId trainingSurveyId = new TrainingSurveyId();

    @Embedded
    @AttributeOverride(name = "uuid", column = @Column(name = "athlete_uuid", nullable = false))
    private AthleteId athleteId;

    @Embedded
    private BaseInformation baseInformation;

    @Embedded
    private HealthInformation healthInformation;

    @Embedded
    private NutritionInformation nutritionInformation;

    @Embedded
    private TrainingGoal runningTrainingGoal;

    private TrainingSurvey(AthleteId athleteId,
                           BaseInformation baseInformation,
                           HealthInformation healthInformation,
                           NutritionInformation nutritionInformation,
                           TrainingGoal trainingGoals) {
        this.athleteId = athleteId;
        this.baseInformation = baseInformation;
        this.healthInformation = healthInformation;
        this.nutritionInformation = nutritionInformation;
        this.runningTrainingGoal = trainingGoals;
    }

    public static TrainingSurvey of(AthleteId athleteId,
                                    BaseInformation baseInformation,
                                    HealthInformation healthInformation,
                                    NutritionInformation nutritionInformation,
                                    TrainingGoal trainingGoals) {
        return new TrainingSurvey(
            athleteId,
            baseInformation,
            healthInformation,
            nutritionInformation,
            trainingGoals);
    }


    public TrainingHistory addTrainingHistoryToSurvey(Distance distance,
                                                      Duration personalRecord,
                                                      Duration lastTime) {
        return new TrainingHistory(
            distance,
            personalRecord,
            lastTime,
            this.trainingSurveyId
        );
    }

    protected TrainingSurvey() {
        super();
    }

    public void removeTrainingHistoryFromSurvey(Long trainingHistoryId) {
    }

    public TrainingIntensityInformation addTrainingIntensityPlanToSurvey(
        DayOfWeek dayOfWeek,
        TrainingIntensity trainingIntensity) {
        return new TrainingIntensityInformation(
            dayOfWeek,
            trainingIntensity,
            this.id()
        );
    }


    public BaseInformation baseInformation() {
        return this.baseInformation;
    }

    public HealthInformation healthInformation() {
        return this.healthInformation;
    }

    public NutritionInformation nutritionInformation() {
        return this.nutritionInformation;
    }

    public TrainingGoal trainingGoal() {
        return this.runningTrainingGoal;
    }

    public String trainingSurveyId() {
        return this.trainingSurveyId.uuid();
    }

    public void update(BaseInformation baseInformation,
                       HealthInformation healthInformation,
                       NutritionInformation nutritionInformation) {
        this.baseInformation = baseInformation;
        this.healthInformation = healthInformation;
        this.nutritionInformation = nutritionInformation;
    }
}
