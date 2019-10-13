package com.athleticspot.training.infrastracture.web;

import com.athleticspot.AthleticspotApp;
import com.athleticspot.common.domain.model.MetricSystemType;
import com.athleticspot.domain.UserId;
import com.athleticspot.infrastracture.web.rest.errors.ExceptionTranslator;
import com.athleticspot.service.UserService;
import com.athleticspot.training.application.TrainingSurveyApplicationService;
import com.athleticspot.training.domain.trainingsurvey.*;
import com.athleticspot.training.infrastracture.dto.in.AssignTrainingSurveyInDto;
import com.athleticspot.training.infrastracture.dto.in.BaseInformationInDtoAssembler;
import com.athleticspot.web.rest.TestUtil;
import io.github.jhipster.config.JHipsterConstants;
import org.assertj.core.api.Assertions;
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
import java.util.Optional;

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

    @Autowired
    private BaseInformationInDtoAssembler baseInformationInDtoAssembler;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        TrainingSurveyApplicationService trainingSurveyApplicationService = new TrainingSurveyApplicationService(
            trainingSurveyRepository,
            userService,
            trainingHistoryRepository,
            trainingSurveyProvider,
            baseInformationInDtoAssembler);

        TrainingSurveyCommandController trainingSurveyCommandController =
            new TrainingSurveyCommandController(trainingSurveyApplicationService, userService);

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
        final String userId = "48554332-54af-4747-addb-7d753b3915d4";

        this.mockMvc.perform(post(ApiUrl.SURVEY_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(createAssignTrainingSurveyDto())))
            .andDo(print())
            .andExpect(status().isOk());

        final Optional<TrainingSurvey> trainingSurveyOptional = trainingSurveyRepository.findByUserId(new UserId(userId));
        Assertions.assertThat(trainingSurveyOptional).isNotEmpty();
        Assertions.assertThat(userService.getUserWithAuthorities().getMetricSystemType()).isEqualTo(MetricSystemType.IMPERIAL);
    }

    private AssignTrainingSurveyInDto createAssignTrainingSurveyDto() {

        return new AssignTrainingSurveyInDto
        .AssignTrainingSurveyInDtoBuilder()
        .setBaseInformation(new BaseInformation(
            LocalDate.now(),
            70d,
            173d,
            MetricSystemType.IMPERIAL))
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

    }

}
