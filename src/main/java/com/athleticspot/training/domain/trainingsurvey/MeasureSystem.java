package com.athleticspot.training.domain.trainingsurvey;

import com.athleticspot.training.domain.MeasureSystemType;

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
    MeasureSystemType measureSystemType;

    public MeasureSystem() {
        super();
    }

    public MeasureSystem(MeasureSystemType measureSystemType) {
        this.measureSystemType = measureSystemType;
    }

    public String getMassUnit() {
        if (measureSystemType.equals(MeasureSystemType.Metric)) {
            return "kg";
        } else {
            return "pound";
        }
    }

    public String getHeightUnit() {
        if (measureSystemType.equals(MeasureSystemType.Metric)) {
            return "cm";
        } else {
            return "foot";
        }
    }
}
