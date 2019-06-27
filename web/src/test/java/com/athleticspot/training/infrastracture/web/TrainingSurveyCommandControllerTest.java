package com.athleticspot.training.infrastracture.web;

import com.athleticspot.AthleticspotApp;
import com.athleticspot.common.domain.model.MetricSystemType;
import com.athleticspot.service.UserService;
import com.athleticspot.training.application.TrainingSurveyApplicationService;
import com.athleticspot.training.domain.trainingsurvey.*;
import com.athleticspot.training.infrastracture.dto.in.AssignTrainingSurveyInDto;
import com.athleticspot.web.rest.TestUtil;
import com.athleticspot.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.config.JHipsterConstants;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Tomasz Kasprzycki
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AthleticspotApp.class)
@ActiveProfiles(value = {JHipsterConstants.SPRING_PROFILE_TEST})
public class TrainingSurveyCommandControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private HttpMessageConverter[] httpMessageConverters;

    private TrainingSurveyApplicationService trainingSurveyApplicationService;

    @Autowired
    TrainingSurveyRepository trainingSurveyRepository;

    @Autowired
    UserService userService;

    @Autowired
    TrainingHistoryRepository trainingHistoryRepository;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private TrainingSurveyProvider trainingSurveyProvider;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.trainingSurveyApplicationService = new TrainingSurveyApplicationService(
            trainingSurveyRepository,
            userService,
            trainingHistoryRepository,
            trainingSurveyProvider);

        TrainingSurveyCommandController trainingSurveyCommandController =
            new TrainingSurveyCommandController(trainingSurveyApplicationService);

        this.mockMvc = MockMvcBuilders.standaloneSetup(trainingSurveyCommandController)
            .setMessageConverters(httpMessageConverters)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .build();
    }

    @Test
    @WithMockUser("user")
    @Transactional
    public void testCreatingAndAssigningTrainingSurveyToAthlete() throws Exception {

        this.mockMvc.perform(post(ApiUrl.SURVEY_URL)
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
                173d,
                MetricSystemType.METRIC))
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
