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
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "athlete_seq", allocationSize = 1)
public class Athlete extends IdentifiedDomainObject {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(length = 100, unique = true, nullable = false)
    private String name;

    @Column(name = "athlete_id")
    private AthleteId athleteId;

    @OneToOne
    @JoinColumn(name = "USER_ID", unique = true, nullable = false, updatable = false)
    private User user;

    public TrainingSurvey assignSurvey(
        BaseInformation baseInformation,
        HealthInformation healthInformation,
        NutritionInformation nutritionInformation,
        TrainingGoal trainingGoals,
        MeasureSystemType measureSystemType) {
        TrainingSurvey trainingSurvey = new TrainingSurvey(
            this.athleteId(),
            baseInformation,
            healthInformation,
            nutritionInformation,
            trainingGoals,
            new MeasureSystem(measureSystemType));

//        DomainEventPublisher
//            .instance()
//            .publish(new SurveyAssignedToAthlete(
//                this.getId(),
//                new TrainingSurveyId(trainingSurvey.getId()),
//                baseInformation,
//                healthInformation,
//                nutritionInformation,
//                trainingGoals));

        return trainingSurvey;
    }

//    public Training assignTrainingToAthlete(
//        RunCategory trainingDistance,
//        RaceResult raceResult) {
//        return new Training(
//            trainingDistance,
//            raceResult
//        );
//        //        DomainEventPublisher
//            .instance()
////            .publish();
//    }

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
