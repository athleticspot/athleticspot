package com.athleticspot.training.domain.trainingsurvey;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Tomasz Kasprzycki
 */
@Embeddable
public class TrainingGoal {

    @Column(name = "distance", nullable = true)
    private Double distance;

    @Column(name = "duration", nullable = true)
    private Double duration;

    @Column(name = "run_category", nullable = true)
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

    public Double getDistance() {
        return distance;
    }

    public Double getDuration() {
        return duration;
    }

    public RunCategory getRunCategory() {
        return runCategory;
    }
}
