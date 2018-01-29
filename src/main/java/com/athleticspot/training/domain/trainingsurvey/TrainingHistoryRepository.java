package com.athleticspot.training.domain.trainingsurvey;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Tomasz Kasprzycki
 */
public interface TrainingHistoryRepository extends JpaRepository<TrainingHistory, Long> {

    Optional<TrainingHistory> findById(Long id);
}
