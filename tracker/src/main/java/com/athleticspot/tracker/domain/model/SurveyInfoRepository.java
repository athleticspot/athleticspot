package com.athleticspot.tracker.domain.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Tomasz Kasprzycki
 */
@Repository
public interface SurveyInfoRepository extends JpaRepository<SurveyInfo, Long> {
}
