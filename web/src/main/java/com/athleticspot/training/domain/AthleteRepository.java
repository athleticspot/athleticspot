package com.athleticspot.training.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Tomasz Kasprzycki
 */
public interface AthleteRepository extends JpaRepository<Athlete, Long> {

    Optional<Athlete> findByUserId(Long userId);

    void deleteByUserId(Long id);

}
