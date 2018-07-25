package com.athleticspot.training.domain.trainingsurvey;


import com.athleticspot.training.domain.TrainingIntensity;

import javax.persistence.*;
import java.time.DayOfWeek;

/**
 * @author Tomasz Kasprzycki
 *         <p>
 *         Class contains information about training intensity which users provides
 *         when they fullfil trainng survey
 */
@Entity
@Table(name = "intensity_info")
public class TrainingIntensityInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @Enumerated
    private DayOfWeek dayOfWeek;

    @Column
    @Enumerated
    private TrainingIntensity trainingIntensity;

    @Column
    private Long trainingSurveyId;

    public TrainingIntensityInformation(
        DayOfWeek dayOfWeek,
        TrainingIntensity trainingIntensity,
        Long trainingSurveyId) {
        this.dayOfWeek = dayOfWeek;
        this.trainingIntensity = trainingIntensity;
        this.trainingSurveyId = trainingSurveyId;
    }

    public void updateTrainingIntensityPlan(DayOfWeek dayOfWeek, TrainingIntensity trainingIntensity) {
        this.dayOfWeek = dayOfWeek;
        this.trainingIntensity = trainingIntensity;
//        DomainEventPublisher
//            .instance()
//            .publish(
//                new TrainingIntensityPlanUpdated(
//                    id,
//                    dayOfWeek,
//                    trainingIntensity,
//                    trainingSurveyId
//                )
//            );
    }

    public void delete() {
//        DomainEventPublisher
//            .instance()
//            .publish(new
//                TrainingIntensityPlanRemoved(
//                this.id
//            ));
    }

    public Long getId() {
        return id;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public TrainingIntensity getTrainingIntensity() {
        return trainingIntensity;
    }

    public Long getTrainingSurveyId() {
        return trainingSurveyId;
    }
}
