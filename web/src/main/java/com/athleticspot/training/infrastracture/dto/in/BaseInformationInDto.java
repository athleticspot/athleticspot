package com.athleticspot.training.infrastracture.dto.in;

import com.athleticspot.common.domain.model.MetricSystemType;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Tomasz Kasprzycki
 */
@JsonDeserialize
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class BaseInformationInDto implements Serializable {

    private LocalDate birthDay;

    private Double bodyMass;

    private Double height;

    private MetricSystemType metricSystemType;

    public BaseInformationInDto() {
    }

    public BaseInformationInDto(LocalDate birthDay, Double bodyMass, Double height, MetricSystemType metricSystemType) {
        this.birthDay = birthDay;
        this.bodyMass = bodyMass;
        this.height = height;
        this.metricSystemType = metricSystemType;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public Double getBodyMass() {
        return bodyMass;
    }

    public Double getHeight() {
        return height;
    }

    public MetricSystemType getMetricSystemType() {
        return metricSystemType;
    }

    @Override
    public String toString() {
        return "BaseInformationInDto{" +
            "birthDay=" + birthDay +
            ", bodyMass=" + bodyMass +
            ", height=" + height +
            ", metricSystemType=" + metricSystemType +
            '}';
    }
}
