package com.athleticspot.training.domain.trainingsurvey;

import javax.persistence.Column;
import javax.persistence.Embeddable;
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

    protected BaseInformation() {
        super();
    }

    public BaseInformation(LocalDate birthDay,
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

    @Override
    public String toString() {
        return "BaseInformation{" +
            "birthDay=" + birthDay +
            ", bodyMass=" + bodyMass +
            ", height=" + height +
            '}';
    }
}
