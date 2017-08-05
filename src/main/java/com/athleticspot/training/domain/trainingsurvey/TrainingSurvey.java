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
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
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
    private MeasureSystem measureSystem;

    @Embedded
    private BaseInformation baseInformation;

    @Embedded
    private HealthInformation healthInformation;

    @Embedded
    private NutritionInformation nutritionInformation;

    @Embedded
    private TrainingGoal trainingGoal;

    public TrainingSurvey(
        AthleteId athleteId,
        BaseInformation baseInformation,
        HealthInformation healthInformation,
        NutritionInformation nutritionInformation,
        TrainingGoal trainingGoals,
        MeasureSystem measureSystem) {
        this.baseInformation = baseInformation;
        this.healthInformation = healthInformation;
        this.nutritionInformation = nutritionInformation;
        this.trainingGoal = trainingGoals;
        this.measureSystem = measureSystem;
    }


    public TrainingHistory addTrainingHistoryToSurvey(
        Distance distance,
        Duration personalRecord,
        Duration lastTime) {
//        DomainEventPublisher
//            .instance()
//            .publish(new TrainingHistoryAssignedToSurvey(
//                distance,
//                personalRecord,
//                lastTime,
//                new TrainingSurveyId(this.getId())
//            ));
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
//        DomainEventPublisher
//            .instance()
//            .publish(new TrainingHistoryRemovedFromSurvey(
//                trainingHistoryId,
//                this.id
//            ));
    }

    public TrainingIntensityInformation addTrainingIntensityPlanToSurvey(
        DayOfWeek dayOfWeek,
        TrainingIntensity trainingIntensity) {
//        DomainEventPublisher
//            .instance()
//            .publish(new TrainingIntensityPlanAssignedToSurvey(
//                dayOfWeek,
//                trainingIntensity,
//                new TrainingSurveyId(this.getId())
//            ));
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
        return this.trainingGoal;
    }

    public MeasureSystem measureSystem() {
        return this.measureSystem;
    }

    public String trainingSurveyId() {
        return this.trainingSurveyId.uuid();
    }

}
