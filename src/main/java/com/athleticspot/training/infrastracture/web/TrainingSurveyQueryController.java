package com.athleticspot.training.infrastracture.web;

import com.athleticspot.training.domain.trainingsurvey.TrainingSurveyProvider;
import com.athleticspot.training.infrastracture.dto.out.TrainingSurveyOutDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tomasz Kasprzycki
 */
@RestController
@RequestMapping(value = ApiUrl.SURVEY_URL)
public class TrainingSurveyQueryController {

    private final TrainingSurveyProvider trainingSurveyProvider;

    @Autowired
    public TrainingSurveyQueryController(
        TrainingSurveyProvider trainingSurveyProvider) {
        this.trainingSurveyProvider = trainingSurveyProvider;
    }

    @GetMapping
    public TrainingSurveyOutDto getTrainingSurvey() {
        trainingSurveyProvider.getAthleteSurvey();
        return null;
    }

}
