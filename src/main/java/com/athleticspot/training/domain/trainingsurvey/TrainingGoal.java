package com.athleticspot.training.domain.trainingsurvey;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Tomasz Kasprzycki
 */
@Embeddable
public class TrainingGoal {

    @Column(nullable = false)
    private Double distance;

    @Column(nullable = false)
    private Double duration;

    @Column(nullable = false)
    private RunCategory runCategory;

    protected TrainingGoal() {
        super();
    }

    public TrainingGoal(
        Double distance,
        Double duration,
        RunCategory runCategory) {
        this.distance = distance;
        this.duration = duration;
        this.runCategory = runCategory;
    }
}
