package com.athleticspot.training.domain.trainingsurvey;

import java.util.Optional;

/**
 * @author Tomasz Kasprzycki
 */
public interface TrainingSurveyProvider {

    Optional<TrainingSurvey> getAthleteSurvey();
}
