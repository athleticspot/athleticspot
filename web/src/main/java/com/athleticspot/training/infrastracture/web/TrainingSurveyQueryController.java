package com.athleticspot.training.infrastracture.web;

import com.athleticspot.training.domain.trainingsurvey.TrainingSurvey;
import com.athleticspot.training.domain.trainingsurvey.TrainingSurveyProvider;
import com.athleticspot.training.infrastracture.dto.out.TrainingSurveyOutDto;
import com.athleticspot.training.infrastracture.dto.out.TrainingSurveyOutDtoAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author Tomasz Kasprzycki
 */
@RestController
@RequestMapping(value = ApiUrl.SURVEY_URL)
public class TrainingSurveyQueryController {

    private final TrainingSurveyProvider trainingSurveyProvider;

    private final TrainingSurveyOutDtoAssembler trainingSurveyOutDtoAssembler;

    @Autowired
    public TrainingSurveyQueryController(TrainingSurveyProvider trainingSurveyProvider,
                                         TrainingSurveyOutDtoAssembler trainingSurveyOutDtoAssembler) {
        this.trainingSurveyProvider = trainingSurveyProvider;
        this.trainingSurveyOutDtoAssembler = trainingSurveyOutDtoAssembler;
    }

    @GetMapping
    public ResponseEntity<TrainingSurveyOutDto> getTrainingSurvey() {
        final Optional<TrainingSurvey> athleteSurvey = trainingSurveyProvider.getAthleteSurvey();
        return athleteSurvey
            .map(trainingSurvey -> new ResponseEntity<>(trainingSurveyOutDtoAssembler.assemble(trainingSurvey), HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
