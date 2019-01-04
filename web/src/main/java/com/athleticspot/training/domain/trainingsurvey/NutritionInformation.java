package com.athleticspot.training.domain.trainingsurvey;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

/**
 * @author Tomasz Kasprzycki
 */
@Embeddable
public class NutritionInformation {

    @Column(name = "meat_acceptance")
    private boolean meatAcceptance;

    @Column(name = "dairyed_acceptance")
    private boolean dairyedAcceptance;

    @Column(name = "allergies")
    private boolean allergies;

    @Column(name = "food_intolerance")
    private boolean foodIntolerance;

    protected NutritionInformation() {
        super();
    }

    public NutritionInformation(boolean meatAcceptance, boolean dairyedAcceptance, boolean allergies, boolean foodIntolerance) {
        this.meatAcceptance = meatAcceptance;
        this.dairyedAcceptance = dairyedAcceptance;
        this.allergies = allergies;
        this.foodIntolerance = foodIntolerance;
    }

    public boolean isMeatAcceptance() {
        return meatAcceptance;
    }

    public boolean isDairyedAcceptance() {
        return dairyedAcceptance;
    }

    public boolean isAllergies() {
        return allergies;
    }

    public boolean isFoodIntolerance() {
        return foodIntolerance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NutritionInformation that = (NutritionInformation) o;
        return meatAcceptance == that.meatAcceptance &&
            dairyedAcceptance == that.dairyedAcceptance &&
            allergies == that.allergies &&
            foodIntolerance == that.foodIntolerance;
    }

    @Override
    public int hashCode() {
        return Objects.hash(meatAcceptance, dairyedAcceptance, allergies, foodIntolerance);
    }


}
