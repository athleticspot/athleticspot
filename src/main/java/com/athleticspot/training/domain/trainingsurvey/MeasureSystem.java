package com.athleticspot.training.domain.trainingsurvey;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Enumerated;

/**
 * @author Tomasz Kasprzycki
 */
@Embeddable
public class MeasureSystem {

    @Column(nullable = false)
    @Enumerated
    MeasureType measureType;

    public MeasureSystem() {
        super();
    }

    public MeasureSystem(MeasureType measureType) {
        this.measureType = measureType;
    }

    public String getMassUnit() {
        if (measureType.equals(MeasureType.Metric)) {
            return "kg";
        } else {
            return "pound";
        }
    }

    public String getHeightUnit() {
        if (measureType.equals(MeasureType.Metric)) {
            return "cm";
        } else {
            return "foot";
        }
    }
}
