package com.athleticspot.training.domain.trainingsurvey;

import com.athleticspot.common.domain.model.IdentifiedDomainObject;
import com.athleticspot.training.domain.AthleteId;
import org.springframework.data.geo.Distance;

import javax.persistence.*;
import java.time.Duration;

/**
 * @author Tomasz Kasprzycki
 */
@Entity
@Table(name = "training_survey")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "training_survey_seq", allocationSize = 1)
public class TrainingSurvey extends IdentifiedDomainObject {

    private static final long serialVersionUID = 1L;

    @Embedded
    @AttributeOverride(name = "uuid", column = @Column(name = "survey_uuid", nullable = false))
    private TrainingSurveyId trainingSurveyId = new TrainingSurveyId();

    @Embedded
    @AttributeOverride(name = "uuid", column = @Column(name = "athlete_uuid", nullable = false))
    private AthleteId athleteId;

    @Embedded
    private BaseInformation baseInformation;

    @Embedded
    private HealthInformation healthInformation;

    @Embedded
    private NutritionInformation nutritionInformation;

    private TrainingSurvey(AthleteId athleteId,
                           BaseInformation baseInformation,
                           HealthInformation healthInformation,
                           NutritionInformation nutritionInformation) {
        this.athleteId = athleteId;
        this.baseInformation = baseInformation;
        this.healthInformation = healthInformation;
        this.nutritionInformation = nutritionInformation;
    }

    public static TrainingSurvey of(AthleteId athleteId,
                                    BaseInformation baseInformation,
                                    HealthInformation healthInformation,
                                    NutritionInformation nutritionInformation) {
        return new TrainingSurvey(
            athleteId,
            baseInformation,
            healthInformation,
            nutritionInformation
        );
    }


    public TrainingHistory addTrainingHistoryToSurvey(Distance distance,
                                                      Duration personalRecord,
                                                      Duration lastTime) {
        return new TrainingHistory(
            distance,
            personalRecord,
            lastTime,
            this.trainingSurveyId
        );
    }

    protected TrainingSurvey() {
        super();
    }


    public BaseInformation baseInformation() {
        return this.baseInformation;
    }

    public HealthInformation healthInformation() {
        return this.healthInformation;
    }

    public NutritionInformation nutritionInformation() {
        return this.nutritionInformation;
    }

    public String trainingSurveyId() {
        return this.trainingSurveyId.uuid();
    }

    public void update(BaseInformation baseInformation,
                       HealthInformation healthInformation,
                       NutritionInformation nutritionInformation) {
        this.baseInformation = baseInformation;
        this.healthInformation = healthInformation;
        this.nutritionInformation = nutritionInformation;
    }
}
