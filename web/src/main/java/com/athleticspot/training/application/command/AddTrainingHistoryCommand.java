package com.athleticspot.training.application.command;

import com.athleticspot.common.command.ResponseAwareDomainCommand;
import com.athleticspot.training.domain.trainingsurvey.TrainingSurveyId;
import org.springframework.data.geo.Distance;

import java.time.Duration;

/**
 * @author Tomasz Kasprzycki
 */
public class AddTrainingHistoryCommand extends ResponseAwareDomainCommand<Long> {

    private final TrainingSurveyId trainingSurveyId;

    private final Distance distance;

    private final Duration personalRecord;

    private final Duration lastTime;

    private AddTrainingHistoryCommand(TrainingSurveyId trainingSurveyId,
                                      Distance distance,
                                      Duration personalRecord,
                                      Duration lastTime) {
        this.trainingSurveyId = trainingSurveyId;
        this.distance = distance;
        this.personalRecord = personalRecord;
        this.lastTime = lastTime;
    }

    public static AddTrainingHistoryCommand of(TrainingSurveyId trainingSurveyId,
                                               Distance distance,
                                               Duration personalRecord,
                                               Duration lastTime) {
        return new AddTrainingHistoryCommand(trainingSurveyId, distance, personalRecord, lastTime);
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
