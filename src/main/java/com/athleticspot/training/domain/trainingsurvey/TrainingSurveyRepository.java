package com.athleticspot.training.domain.trainingsurvey;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Tomasz Kasprzycki
 */
public interface TrainingSurveyRepository extends JpaRepository<TrainingSurvey, Long> {

    Optional<TrainingSurvey> findByUsername(String username);
}
