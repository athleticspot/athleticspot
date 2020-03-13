package com.athleticspot.training.domain.trainingsurvey;

import com.athleticspot.common.domain.model.MetricSystemType;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author Tomasz Kasprzycki
 */
@Embeddable
public class MeasureSystem {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    MetricSystemType metricSystemType;

    public MeasureSystem() {
        super();
    }

    public MeasureSystem(MetricSystemType metricSystemType) {
        this.metricSystemType = metricSystemType;
    }

    public String getMassUnit() {
        if (metricSystemType.equals(MetricSystemType.METRIC)) {
            return "kg";
        } else {
            return "pound";
        }
    }

    public String getHeightUnit() {
        if (metricSystemType.equals(MetricSystemType.METRIC)) {
            return "cm";
        } else {
            return "foot";
        }
    }
}
