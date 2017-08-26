package com.athleticspot.training.infrastracture.web;

import com.athleticspot.training.application.TrainingSurveyApplicationService;
import com.athleticspot.training.application.command.AssignTrainingSurveyToAthleteCommand;
import com.athleticspot.training.infrastracture.dto.in.AssignTrainingSurveyInDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Tomasz Kasprzycki
 */
@RestController
@RequestMapping(value = ApiUrl.SURVEY_URL)
public class TrainingSurveyCommandController {

    private final TrainingSurveyApplicationService trainingSurveyApplicationService;

    @Autowired
    public TrainingSurveyCommandController(TrainingSurveyApplicationService trainingSurveyApplicationService) {
        this.trainingSurveyApplicationService = trainingSurveyApplicationService;
    }

    @PostMapping
    public void createTrainingSurvey(@Valid @RequestBody AssignTrainingSurveyInDto assignTrainingSurveyInDto) {

        AssignTrainingSurveyToAthleteCommand assignTrainingSurveyToAthleteCommand
            = new AssignTrainingSurveyToAthleteCommand(
            assignTrainingSurveyInDto.getBaseInformation().getBirthday(),
            assignTrainingSurveyInDto.getBaseInformation().getWeight(),
            assignTrainingSurveyInDto.getBaseInformation().getHeight(),
            assignTrainingSurveyInDto.getHealthInformation().getHealthContraindications(),
            assignTrainingSurveyInDto.getHealthInformation().getStressTest(),
            assignTrainingSurveyInDto.getHealthInformation().getBloodTest(),
            assignTrainingSurveyInDto.getHealthInformation().getHoursOfSleep(),
            assignTrainingSurveyInDto.getTrainingGoal().getDuration(),
            assignTrainingSurveyInDto.getTrainingGoal().getDistance(),
            assignTrainingSurveyInDto.getTrainingGoal().getRunCategory(),
            assignTrainingSurveyInDto.getNutritionInformation().isMeatAcceptance(),
            assignTrainingSurveyInDto.getNutritionInformation().isDairyedAcceptance(),
            assignTrainingSurveyInDto.getNutritionInformation().isAllergies(),
            assignTrainingSurveyInDto.getNutritionInformation().isFoodIntolerance(),
            assignTrainingSurveyInDto.getMeasureSystemType()

        );
        trainingSurveyApplicationService.assignTrainingSurveyToAthlete(assignTrainingSurveyToAthleteCommand);
    }

    @PutMapping
    public void updateTrainingSurvey() {

    }

    @DeleteMapping
    public void deleteTrainingSurvey() {

    }

}
