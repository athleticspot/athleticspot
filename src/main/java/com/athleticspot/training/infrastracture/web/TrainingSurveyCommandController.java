package com.athleticspot.training.infrastracture.web;

import com.athleticspot.training.application.TrainingSurveyApplicationService;
import com.athleticspot.training.application.command.AssignTrainingSurveyToAthleteCommand;
import com.athleticspot.training.infrastracture.dto.in.AssignTrainingSurveyInDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public void createTrainingSurvey(@RequestBody AssignTrainingSurveyInDto assignTrainingSurveyInDto) {

        AssignTrainingSurveyToAthleteCommand assignTrainingSurveyToAthleteCommand
            = new AssignTrainingSurveyToAthleteCommand(
            null,
            null,
            null,
            true,
            true,
            true,
            null,
            null,
            null,
            null,
            false,
            false,
            false,
            false,
            null

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
