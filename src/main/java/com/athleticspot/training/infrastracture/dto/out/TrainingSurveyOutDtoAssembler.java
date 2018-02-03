package com.athleticspot.training.infrastracture.dto.out;

import com.athleticspot.training.domain.trainingsurvey.TrainingSurvey;
import org.springframework.stereotype.Component;

/**
 * @author Tomasz Kasprzycki
 */
@Component
public class TrainingSurveyOutDtoAssembler {

    public TrainingSurveyOutDto assemble(TrainingSurvey trainingSurvey) {
        return new TrainingSurveyOutDto()
            .trainingSurveyUuid(trainingSurvey.trainingSurveyId())
            .baseInformation(trainingSurvey.baseInformation())
            .healthInformation(trainingSurvey.healthInformation())
            .nutritionInformation(trainingSurvey.nutritionInformation());

    }
}
