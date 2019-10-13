package com.athleticspot.training.application;

import com.athleticspot.AthleticspotApp;
import com.athleticspot.common.domain.model.MetricSystemType;
import com.athleticspot.training.application.command.AddTrainingHistoryCommand;
import com.athleticspot.training.application.command.AssignTrainingSurveyToAthleteCommand;
import com.athleticspot.training.application.command.UpdateTrainingSurveyCommand;
import com.athleticspot.training.application.exception.SurveyAlreadyAssignException;
import com.athleticspot.training.domain.trainingsurvey.*;
import com.athleticspot.training.infrastracture.dto.in.BaseInformationInDto;
import io.github.jhipster.config.JHipsterConstants;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;

/**
 * @author Tomasz Kasprzycki
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AthleticspotApp.class)
@ActiveProfiles(value = {JHipsterConstants.SPRING_PROFILE_TEST})
@Transactional
public class TrainingSurveyApplicationServiceTest {

    private SecurityContext securityContext;

    @Autowired
    TrainingSurveyApplicationService trainingSurveyApplicationService;

    @Autowired
    TrainingSurveyRepository trainingSurveyRepository;

    @Autowired
    TrainingHistoryRepository trainingHistoryRepository;

    @Before
    public void config() throws Exception {
        trainingSurveyRepository.deleteAll();
        securityContext = SecurityContextHolder.createEmptyContext();
        SecurityContextHolder.setContext(securityContext);
        securityContext.setAuthentication(new UsernamePasswordAuthenticationToken("admin", "admin"));
    }

    @Test
    public void when_user_submit_survey_then_its_created_and_assigned_to_him() {
        //given:
        final AssignTrainingSurveyToAthleteCommand assignTrainingSurveyToAthleteCommand =
            createAssignTrainingSurveyToAthleteCommand();

        //when:
        final TrainingSurvey trainingSurvey =
            trainingSurveyApplicationService.assignTrainingSurveyToAthlete(assignTrainingSurveyToAthleteCommand);

        //then:
        final TrainingSurvey savedTrainingSurvey =
            trainingSurveyRepository
                .findByTrainingSurveyIdUuid(assignTrainingSurveyToAthleteCommand.getResponse())
                .get();

        Assertions.assertThat(savedTrainingSurvey).isNotNull();
        Assertions.assertThat(savedTrainingSurvey.baseInformation()).isEqualToComparingFieldByField(assignTrainingSurveyToAthleteCommand.getBaseInformation());
        Assertions.assertThat(savedTrainingSurvey.healthInformation()).isEqualToComparingFieldByField(assignTrainingSurveyToAthleteCommand.getHealthInformation());
        Assertions.assertThat(savedTrainingSurvey.nutritionInformation()).isEqualToComparingFieldByField(assignTrainingSurveyToAthleteCommand.getNutritionInformation());
    }

    @Test(expected = SurveyAlreadyAssignException.class)
    public void when_user_wants_to_assigned_different_survey_then_error_is_thrown() {
        //given:
        final AssignTrainingSurveyToAthleteCommand assignTrainingSurveyToAthleteCommand =
            createAssignTrainingSurveyToAthleteCommand();
        final TrainingSurvey trainingSurvey = trainingSurveyApplicationService
            .assignTrainingSurveyToAthlete(
                assignTrainingSurveyToAthleteCommand);

        trainingSurveyApplicationService.assignTrainingSurveyToAthlete(assignTrainingSurveyToAthleteCommand);

    }

    @Test
    public void when_survey_is_updated_then_new_values_are_assigned() {
        //given
        final UpdateTrainingSurveyCommand updateTrainingSurveyCommand = createUpdateTrainingSurveyCommand();
        final AssignTrainingSurveyToAthleteCommand assignTrainingSurveyToAthleteCommand = createAssignTrainingSurveyToAthleteCommand();

        trainingSurveyApplicationService.assignTrainingSurveyToAthlete(assignTrainingSurveyToAthleteCommand);

        //when:
        trainingSurveyApplicationService.updateTrainingSurvey(updateTrainingSurveyCommand);

        //then
        final TrainingSurvey savedTrainingSurvey = trainingSurveyRepository.findByTrainingSurveyIdUuid(assignTrainingSurveyToAthleteCommand.getResponse()).get();
        Assertions.assertThat(savedTrainingSurvey.baseInformation()).isEqualToComparingFieldByField(updateTrainingSurveyCommand.getBaseInformation());
        Assertions.assertThat(savedTrainingSurvey.nutritionInformation()).isEqualToComparingFieldByField(updateTrainingSurveyCommand.getNutritionInformation());
        Assertions.assertThat(savedTrainingSurvey.healthInformation()).isEqualToComparingFieldByField(updateTrainingSurveyCommand.getHealthInformation());

    }

    @Test
    public void assigningTrainingHistoryTest() {
        final TrainingSurvey trainingSurvey = createTrainingSurvey();
        final AddTrainingHistoryCommand addTrainingHistoryCommand = AddTrainingHistoryCommand.of(
            new TrainingSurveyId(trainingSurvey.trainingSurveyId()),
            new Distance(25, Metrics.KILOMETERS),
            Duration.ofSeconds(120),
            Duration.ofSeconds(150)
        );
        trainingSurveyApplicationService.addTrainingHistory(
            addTrainingHistoryCommand
        );

        final TrainingHistory trainingHistory
            = trainingHistoryRepository.findById(addTrainingHistoryCommand.getResponse()).get();


        Assertions.assertThat(trainingHistory).isNotNull();

    }


    //Factory functions

    public AssignTrainingSurveyToAthleteCommand createAssignTrainingSurveyToAthleteCommand() {
        HealthInformation healthInformation = createHealthInformation();
        boolean meat_acceptance = false;
        boolean dairiesAcceptance = true;
        boolean allergies = true;
        boolean foodIntolerance = true;
        return AssignTrainingSurveyToAthleteCommand
            .of(
                createBaseInformation().getBirthDay(),
                createBaseInformation().getBodyMass(),
                createBaseInformation().getHeight(),
                healthInformation.getHealthContraindications(),
                healthInformation.getStressTest(),
                healthInformation.getBloodTest(),
                healthInformation.getHoursOfSleep(),
                meat_acceptance,
                dairiesAcceptance,
                allergies,
                foodIntolerance);
    }

    public UpdateTrainingSurveyCommand createUpdateTrainingSurveyCommand() {
        BaseInformationInDto baseInformation = new BaseInformationInDto(
            LocalDate.parse("2017-02-01"),
            25d,
            180d,
            MetricSystemType.METRIC);
        HealthInformation healthInformation = new HealthInformation(
            false,
            true,
            true,
            22d
        );
        NutritionInformation nutritionInformation = new NutritionInformation(
            false,
            true,
            false,
            false
        );


        return UpdateTrainingSurveyCommand.create(
            baseInformation,
            healthInformation,
            nutritionInformation
        );
    }

    public TrainingSurvey createTrainingSurvey() {
        HealthInformation healthInformation = createHealthInformation();
        boolean meat_acceptance = false;
        boolean dairiesAcceptance = true;
        boolean allergies = true;
        boolean foodIntolerance = true;
        return trainingSurveyApplicationService
            .assignTrainingSurveyToAthlete(
                AssignTrainingSurveyToAthleteCommand.of(
                    createBaseInformation().getBirthDay(),
                    createBaseInformation().getBodyMass(),
                    createBaseInformation().getHeight(),
                    healthInformation.getHealthContraindications(),
                    healthInformation.getStressTest(),
                    healthInformation.getBloodTest(),
                    healthInformation.getHoursOfSleep(),
                    meat_acceptance,
                    dairiesAcceptance,
                    allergies,
                    foodIntolerance
                ));
    }

    public static HealthInformation createHealthInformation() {
        boolean healthContraindications = false;
        boolean stressTest = false;
        boolean bloodTest = false;
        return new HealthInformation(
            healthContraindications,
            stressTest,
            bloodTest,
            8d);
    }

    public static BaseInformation createBaseInformation() {
        return new BaseInformation(
            LocalDate.now(),
            60d,
            172d,
            MetricSystemType.METRIC);
    }


}
