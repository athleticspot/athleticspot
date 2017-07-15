package com.athleticspot.training.domain.trainingsurvey;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

/**
 * @author Tomasz Kasprzycki
 */
@Embeddable
public class HealthInformation {

    @Column(nullable = false)
    private boolean healthContraindications;

    @Column(nullable = false)
    private boolean stressTest;

    @Column(nullable = false)
    private boolean bloodTest;

    @Column(nullable = false)
    private Double hoursOfSleep;

    protected HealthInformation() {
        super();
    }

    public HealthInformation(boolean healthContraindications, boolean stressTest, boolean bloodTest, Double hoursOfSleep) {
        this.healthContraindications = healthContraindications;
        this.stressTest = stressTest;
        this.bloodTest = bloodTest;
        this.hoursOfSleep = hoursOfSleep;
    }

    public boolean getHealthContraindications() {
        return healthContraindications;
    }

    public boolean getStressTest() {
        return stressTest;
    }

    public boolean getBloodTest() {
        return bloodTest;
    }

    public Double getHoursOfSleep() {
        return hoursOfSleep;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HealthInformation that = (HealthInformation) o;
        return healthContraindications == that.healthContraindications &&
            stressTest == that.stressTest &&
            bloodTest == that.bloodTest &&
            Objects.equals(hoursOfSleep, that.hoursOfSleep);
    }

    @Override
    public int hashCode() {
        return Objects.hash(healthContraindications, stressTest, bloodTest, hoursOfSleep);
    }
}
