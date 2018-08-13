package com.athleticspot.training.domain;

import com.athleticspot.common.domain.model.IdentifiedDomainObject;
import com.athleticspot.domain.User;
import com.athleticspot.training.domain.trainingsurvey.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Tomasz Kasprzycki
 */
@Entity
@Table(name = "athlete")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "athlete_seq", allocationSize = 1)
public class Athlete extends IdentifiedDomainObject {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(length = 100, unique = true, nullable = false)
    private String name;

    @Embedded
    @AttributeOverride(name = "uuid", column = @Column(name = "athlete_id", nullable = false))
    private AthleteId athleteId;

    @OneToOne
    @JoinColumn(name = "USER_ID", unique = true, nullable = false, updatable = false)
    private User user;

    public TrainingSurvey assignSurvey(BaseInformation baseInformation,
                                       HealthInformation healthInformation,
                                       NutritionInformation nutritionInformation,
                                       TrainingGoal trainingGoals) {
        TrainingSurvey trainingSurvey = TrainingSurvey.of(
            this.athleteId(),
            baseInformation,
            healthInformation,
            nutritionInformation,
            trainingGoals);

        return trainingSurvey;
    }


    public String getName() {
        return name;
    }

    public AthleteId athleteId() {
        return athleteId;
    }

    public Athlete setName(String name) {
        this.name = name;
        return this;
    }
}
