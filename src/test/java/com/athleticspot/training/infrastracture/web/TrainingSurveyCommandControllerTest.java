package com.athleticspot.training.infrastracture.web;

import com.athleticspot.AthleticspotApp;
import com.athleticspot.service.UserService;
import com.athleticspot.training.application.TrainingSurveyApplicationService;
import com.athleticspot.training.domain.AthleteRepository;
import com.athleticspot.training.domain.MetricSystemType;
import com.athleticspot.training.domain.trainingsurvey.*;
import com.athleticspot.training.infrastracture.dto.in.AssignTrainingSurveyInDto;
import com.athleticspot.web.rest.TestUtil;
import com.athleticspot.web.rest.errors.ExceptionTranslator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Tomasz Kasprzycki
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AthleticspotApp.class)
public class TrainingSurveyCommandControllerTest {

    private MockMvc mockMvc;


    @Autowired
    private HttpMessageConverter[] httpMessageConverters;

    TrainingSurveyApplicationService trainingSurveyApplicationService;

    @Autowired
    TrainingSurveyRepository trainingSurveyRepository;

    @Mock
    UserService userService;

    @Autowired
    AthleteRepository athleteRepository;

    @Autowired
    TrainingHistoryRepository trainingHistoryRepository;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.trainingSurveyApplicationService = new TrainingSurveyApplicationService(
            trainingSurveyRepository,
            userService,
            athleteRepository,
            trainingHistoryRepository
        );
        TrainingSurveyCommandController trainingSurveyCommandController =
            new TrainingSurveyCommandController(trainingSurveyApplicationService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(trainingSurveyCommandController)
            .setMessageConverters(httpMessageConverters)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .build();
        when(userService.getUserWithAuthorities()).thenReturn(TestUtil.createFakeUser());

    }

    @Test
    public void testCreatingAndAssigningTrainingSurveyToAthlete() throws Exception {

        this.mockMvc.perform(
            post(ApiUrl.SURVEY_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(createAssignTrainingSurveyDto())))
            .andDo(print())
            .andExpect(status().isOk());
    }

    private AssignTrainingSurveyInDto createAssignTrainingSurveyDto() {
        AssignTrainingSurveyInDto assignTrainingSurveyInDto
            = new AssignTrainingSurveyInDto
            .AssignTrainingSurveyInDtoBuilder()
            .setBaseInformation(new BaseInformation(
                LocalDate.now(),
                70d,
                173d
            ))
            .setHealthInformation(new HealthInformation(
                true,
                true,
                true,
                8d
            ))
            .setNutritionInformation(new NutritionInformation(
                true,
                true,
                true,
                true
            ))
            .setTrainingGoal(new TrainingGoal(
                    22d,
                    2d,
                    RunCategory.FIVE_K
                )
            )
            .setMetricSystemType(MetricSystemType.METRIC)
            .build();

        return assignTrainingSurveyInDto;

    }

}
