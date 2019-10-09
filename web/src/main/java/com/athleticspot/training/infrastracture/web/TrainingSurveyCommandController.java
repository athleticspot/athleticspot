package com.athleticspot.training.infrastracture.web;

import com.athleticspot.training.application.TrainingSurveyApplicationService;
import com.athleticspot.training.application.command.AssignTrainingSurveyToAthleteCommand;
import com.athleticspot.training.application.command.UpdateTrainingSurveyCommand;
import com.athleticspot.training.infrastracture.dto.in.AssignTrainingSurveyInDto;
import com.athleticspot.training.infrastracture.dto.in.UpdateTrainingSurveyInDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Tomasz Kasprzycki
 */
@RestController
@RequestMapping(value = ApiUrl.SURVEY_URL)
public class TrainingSurveyCommandController {

    private final Logger log = LoggerFactory.getLogger(TrainingSurveyCommandController.class);

    private final TrainingSurveyApplicationService trainingSurveyApplicationService;

    @Autowired
    public TrainingSurveyCommandController(TrainingSurveyApplicationService trainingSurveyApplicationService) {
        this.trainingSurveyApplicationService = trainingSurveyApplicationService;
    }

    @PostMapping
    public void createTrainingSurvey(@Valid @RequestBody AssignTrainingSurveyInDto assignTrainingSurveyInDto) {

        log.info("Received assign survey dto: {}", assignTrainingSurveyInDto);

        AssignTrainingSurveyToAthleteCommand assignTrainingSurveyToAthleteCommand =
            AssignTrainingSurveyToAthleteCommand.of(assignTrainingSurveyInDto.getBaseInformation().getBirthDay(),
                assignTrainingSurveyInDto.getBaseInformation().getBodyMass(),
                assignTrainingSurveyInDto.getBaseInformation().getHeight(),
                assignTrainingSurveyInDto.getHealthInformation().getHealthContraindications(),
                assignTrainingSurveyInDto.getHealthInformation().getStressTest(),
                assignTrainingSurveyInDto.getHealthInformation().getBloodTest(),
                assignTrainingSurveyInDto.getHealthInformation().getHoursOfSleep(),
                assignTrainingSurveyInDto.getNutritionInformation().isMeatAcceptance(),
                assignTrainingSurveyInDto.getNutritionInformation().isDairyedAcceptance(),
                assignTrainingSurveyInDto.getNutritionInformation().isAllergies(),
                assignTrainingSurveyInDto.getNutritionInformation().isFoodIntolerance());

        trainingSurveyApplicationService.assignTrainingSurveyToAthlete(assignTrainingSurveyToAthleteCommand);

        //TODO: Add command for assiging metric system to the user.
    }


    @PutMapping
    public void updateTrainingSurvey(@Valid @RequestBody UpdateTrainingSurveyInDto updateTrainingSurveyInDto) {
        trainingSurveyApplicationService.updateTrainingSurvey(
            UpdateTrainingSurveyCommand.create(
                updateTrainingSurveyInDto.getBaseInformation(),
                updateTrainingSurveyInDto.getHealthInformation(),
                updateTrainingSurveyInDto.getNutritionInformation()
            )
        );
    }

    @DeleteMapping
    public void deleteTrainingSurvey() {

    }
}
