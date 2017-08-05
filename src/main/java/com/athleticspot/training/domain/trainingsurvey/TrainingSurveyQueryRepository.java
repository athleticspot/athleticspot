package com.athleticspot.training.domain.trainingsurvey;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Tomasz Kasprzycki
 */
public interface TrainingSurveyQueryRepository
    extends JpaRepository<TrainingSurvey, Long> {


}
