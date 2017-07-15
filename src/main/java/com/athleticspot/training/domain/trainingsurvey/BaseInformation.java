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

    @Column(nullable = false)
    private LocalDate birthday;

    @Column(nullable = false)
    private Double weight;

    @Column(nullable = false)
    private Double height;

    protected BaseInformation() {
        super();
    }

    public BaseInformation(
        LocalDate birthday,
        Double weight,
        Double height) {
        this.birthday = birthday;
        this.weight = weight;
        this.height = height;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public Double getWeight() {
        return weight;
    }

    public Double getHeight() {
        return height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseInformation that = (BaseInformation) o;
        return Objects.equals(birthday, that.birthday) &&
            Objects.equals(weight, that.weight) &&
            Objects.equals(height, that.height);
    }

    @Override
    public int hashCode() {
        return Objects.hash(birthday, weight, height);
    }
}
