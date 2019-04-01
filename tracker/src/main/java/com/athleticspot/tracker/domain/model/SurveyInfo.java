package com.athleticspot.tracker.domain.model;

import com.athleticspot.common.domain.model.MetricSystemType;

import javax.persistence.*;

/**
 * @author Tomasz Kasprzycki
 */
@Entity
@Table(name = "training_survey")
@SequenceGenerator(name = "surveyInfoSequenceGenerator", sequenceName = "training_survey_seq", allocationSize = 1)
public class SurveyInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "surveyInfoSequenceGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "athlete_uuid", nullable = false)
    private String athleteId;

    @Column(name = "metric_system_type")
    @Enumerated(EnumType.STRING)
    private MetricSystemType metricSystemType;

    public MetricSystemType getMetricSystemType() {
        return metricSystemType;
    }
}



