package com.athleticspot.training.domain.trainingsurvey;

import org.springframework.data.geo.Distance;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.Duration;

/**
 * @author Tomasz Kasprzycki
 */
@Entity
@Table(name = "training_history")
public class TrainingHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Distance distance;

    @Column
    private Duration personalRecord;

    @Column
    private Duration lastTime;

    @Column
    private Long trainingSurveyId;

    protected TrainingHistory() {

    }

    public TrainingHistory(
        Distance distance,
        Duration personalRecord,
        Duration lastTime,
        Long trainingSurveyId) {
        this.distance = distance;
        this.personalRecord = personalRecord;
        this.lastTime = lastTime;
        this.trainingSurveyId = trainingSurveyId;
    }

    public void updateTrainingHistory(Distance distance, Duration personalRecord, Duration lastTime) {
        Assert.notNull(distance, "distance cannot be null");
        Assert.notNull(personalRecord, "personal record cannot be null");
        Assert.notNull(lastTime, "cannot be null");
        Assert.notNull(this.id, "id cannot be null");
        Assert.notNull(this.trainingSurveyId, "training survey id cannot be null");
        this.distance = distance;
        this.personalRecord = personalRecord;
        this.lastTime = lastTime;
//        DomainEventPublisher
//            .instance()
//            .publish(new TrainingHistoryUpdated(
//                this.id,
//                distance,
//                personalRecord,
//                lastTime,
//                this.trainingSurveyId
//            ));
    }

    public Long getId() {
        return id;
    }

    public Distance getDistance() {
        return distance;
    }

    public Duration getPersonalRecord() {
        return personalRecord;
    }

    public Duration getLastTime() {
        return lastTime;
    }

    public Long getTrainingSurveyId() {
        return trainingSurveyId;
    }
}

