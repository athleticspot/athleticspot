package com.athleticspot.training.application;

import com.athleticspot.domain.User;
import com.athleticspot.service.UserService;
import com.athleticspot.training.application.command.AddTrainingHistoryCommand;
import com.athleticspot.training.application.command.AssignTrainingSurveyToAthleteCommand;
import com.athleticspot.training.application.command.UpdateTrainingSurveyCommand;
import com.athleticspot.training.application.exception.SurveyAlreadyAssignException;
import com.athleticspot.training.domain.AthleteRepository;
import com.athleticspot.training.domain.trainingsurvey.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author Tomasz Kasprzycki
 */
@Service
@Transactional
public class TrainingSurveyApplicationService {

    private final TrainingSurveyRepository trainingSurveyRepository;

    private final UserService userService;

    private final TrainingHistoryRepository trainingHistoryRepository;

    private final TrainingSurveyProvider trainingSurveyProvider;

    @Autowired
    public TrainingSurveyApplicationService(TrainingSurveyRepository trainingSurveyRepository,
                                            UserService userService,
                                            AthleteRepository athleteRepository,
                                            TrainingHistoryRepository trainingHistoryRepository,
                                            TrainingSurveyProvider trainingSurveyProvider) {
        this.trainingSurveyRepository = trainingSurveyRepository;
        this.userService = userService;
        this.trainingHistoryRepository = trainingHistoryRepository;
        this.trainingSurveyProvider = trainingSurveyProvider;
    }

    public TrainingSurvey assignTrainingSurveyToAthlete(AssignTrainingSurveyToAthleteCommand assignTrainingSurveyToAthleteCommand) {
        User athlete = this.userData();
        if (trainingSurveyProvider.getAthleteSurvey().isPresent()) {
            throw new SurveyAlreadyAssignException("Survey allready assigned");
        }
        final TrainingSurvey trainingSurvey =
            athlete.assignSurvey(
                assignTrainingSurveyToAthleteCommand.getBaseInformation(),
                assignTrainingSurveyToAthleteCommand.getHealthInformation(),
                assignTrainingSurveyToAthleteCommand.getNutritionInformation()
            );
        trainingSurveyRepository.save(trainingSurvey);
        assignTrainingSurveyToAthleteCommand.setResponse(trainingSurvey.trainingSurveyId());
        return trainingSurvey;
    }

    public void updateTrainingSurvey(UpdateTrainingSurveyCommand updateTrainingSurveyCommand) {
        User user = this.userData();
        final TrainingSurvey athleteSurvey =
            trainingSurveyProvider
                .getAthleteSurvey()
                .orElseThrow(() -> new IllegalArgumentException("Survey not assigned to user"));
        user.updateSurvey(
            athleteSurvey,
            updateTrainingSurveyCommand.getBaseInformation(),
            updateTrainingSurveyCommand.getHealthInformation(),
            updateTrainingSurveyCommand.getNutritionInformation()
        );
        trainingSurveyRepository.save(athleteSurvey);
    }

    public void addTrainingHistory(AddTrainingHistoryCommand addTrainingHistoryCommand) {
        final TrainingSurvey trainingSurvey =
            trainingSurveyRepository.findByTrainingSurveyIdUuid(
                addTrainingHistoryCommand
                    .getTrainingSurveyId().uuid()).get();

        TrainingHistory trainingHistory = trainingSurvey.addTrainingHistoryToSurvey(
            addTrainingHistoryCommand.getDistance(),
            addTrainingHistoryCommand.getPersonalRecord(),
            addTrainingHistoryCommand.getLastTime()
        );

        trainingHistory = trainingHistoryRepository.save(trainingHistory);
        addTrainingHistoryCommand.setResponse(trainingHistory.getId());
    }

    private User userData() {
        final User userWithAuthorities = userService.getUserWithAuthorities();
        Optional.ofNullable(userWithAuthorities)
            .orElseThrow(() -> new IllegalArgumentException("Logged user cannot be null"));
        return userWithAuthorities;
    }
}
