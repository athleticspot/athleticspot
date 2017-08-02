package com.athleticspot.training.domain.trainingsurvey;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Tomasz Kasprzycki
 */
public interface TrainingHistoryRepository extends JpaRepository<TrainingHistory, Long> {
}
