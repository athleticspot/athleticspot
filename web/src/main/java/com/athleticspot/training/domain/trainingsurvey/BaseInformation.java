package com.athleticspot.training.domain.trainingsurvey;

import com.athleticspot.training.domain.MetricSystemType;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.util.Objects;

/**
 * @author Tomasz Kasprzycki
 */
@Embeddable
public class BaseInformation {

    @Column(name = "birth_day", nullable = false)
    private LocalDate birthDay;

    @Column(name = "body_mass", nullable = false)
    private Double bodyMass;

    @Column(nullable = false)
    private Double height;

    @Column(name ="metric_system_type")
    @Enumerated(EnumType.STRING)
    private MetricSystemType metricSystemType;

    protected BaseInformation() {
        super();
    }

    public BaseInformation(
        LocalDate birthDay,
        Double bodyMass,
        Double height) {
        this.birthDay = birthDay;
        this.bodyMass = bodyMass;
        this.height = height;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseInformation that = (BaseInformation) o;
        return Objects.equals(birthDay, that.birthDay) &&
            Objects.equals(bodyMass, that.bodyMass) &&
            Objects.equals(height, that.height);
    }

    @Override
    public int hashCode() {
        return Objects.hash(birthDay, bodyMass, height);
    }
}
