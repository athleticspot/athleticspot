package com.athleticspot.training.domain.trainingsurvey;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Tomasz Kasprzycki
 */
@Repository
public interface TrainingSurveyRepository extends JpaRepository<TrainingSurvey, Long> {

    Optional<TrainingSurvey> findByTrainingSurveyId(String trainingSurveyId);
}
