package com.athleticspot.training.domain.trainingsurvey;

import com.athleticspot.common.domain.model.IdentifiedDomainObject;
import org.springframework.data.geo.Distance;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.Duration;

/**
 * @author Tomasz Kasprzycki
 */
@Entity
@Table(name = "training_history")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "training_survey_history_seq", allocationSize = 1)
public class TrainingHistory extends IdentifiedDomainObject {

    @Column
    private Distance distance;

    @Column(name = "personal_record")
    private Duration personalRecord;

    @Column(name = "last_time")
    private Duration lastTime;

    @Embedded
    @AttributeOverride(name = "uuid", column = @Column(name = "training_survey_id", nullable = false))
    private TrainingSurveyId trainingSurveyId;

    protected TrainingHistory() {
        super();
    }

    public TrainingHistory(
        Distance distance,
        Duration personalRecord,
        Duration lastTime,
        TrainingSurveyId trainingSurveyId) {
        this.distance = distance;
        this.personalRecord = personalRecord;
        this.lastTime = lastTime;
        this.trainingSurveyId = trainingSurveyId;
    }

    public void updateTrainingHistory(Distance distance, Duration personalRecord, Duration lastTime) {
        Assert.notNull(distance, "distance cannot be null");
        Assert.notNull(personalRecord, "personal record cannot be null");
        Assert.notNull(lastTime, "cannot be null");
        Assert.notNull(this.id(), "id cannot be null");
        Assert.notNull(this.trainingSurveyId, "training survey id cannot be null");
        this.distance = distance;
        this.personalRecord = personalRecord;
        this.lastTime = lastTime;
    }

    public Long getId() {
        return id();
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

    public TrainingSurveyId getTrainingSurveyId() {
        return trainingSurveyId;
    }
}

