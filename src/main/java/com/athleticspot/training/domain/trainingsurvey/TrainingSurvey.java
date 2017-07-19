package com.athleticspot.training.domain.trainingsurvey;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.geo.Distance;

import javax.persistence.*;
import java.time.Duration;

/**
 * @author Tomasz Kasprzycki
 */
@Entity
@Table(name = "training_survey")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TrainingSurvey {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

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
        String username,
        BaseInformation baseInformation,
        HealthInformation healthInformation,
        NutritionInformation nutritionInformation,
        TrainingGoal trainingGoals,
        MeasureSystem measureSystem) {
        this.username = username;
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
            this.getId()
        );
    }
//
//    public TrainingSurvey() {
//    }
//
//    public void removeTrainingHistoryFromSurvey(Long trainingHistoryId) {
//        DomainEventPublisher
//            .instance()
//            .publish(new TrainingHistoryRemovedFromSurvey(
//                trainingHistoryId,
//                this.id
//            ));
//    }
//
//    public TrainingIntensityPlan addTrainingIntensityPlanToSurvey(
//        DayOfWeek dayOfWeek,
//        TrainingIntensity trainingIntensity) {
//        DomainEventPublisher
//            .instance()
//            .publish(new TrainingIntensityPlanAssignedToSurvey(
//                dayOfWeek,
//                trainingIntensity,
//                new TrainingSurveyId(this.getId())
//            ));
//        return new TrainingIntensityPlan(
//            dayOfWeek,
//            trainingIntensity,
//            new TrainingSurveyId(this.getId())
//        );
//    }
//

    public Long getId() {
        return id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public String getUsername() {
        return username;
    }
}
