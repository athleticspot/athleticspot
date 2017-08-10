package com.athleticspot.training.infrastracture.web;

import com.athleticspot.AthleticspotApp;
import com.athleticspot.service.UserService;
import com.athleticspot.training.application.TrainingSurveyApplicationService;
import com.athleticspot.training.domain.AthleteRepository;
import com.athleticspot.training.domain.trainingsurvey.TrainingHistoryRepository;
import com.athleticspot.training.domain.trainingsurvey.TrainingSurveyRepository;
import com.athleticspot.training.infrastracture.dto.in.AssignTrainingSurveyInDto;
import com.athleticspot.web.rest.TestUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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
            .build();
        when(userService.getUserWithAuthorities()).thenReturn(TestUtil.createFakeUser());

    }

    @Test
    public void testCreatingAndAssigningTrainingSurveyToAthlete() throws Exception {
        AssignTrainingSurveyInDto assignTrainingSurveyInDto
            = new AssignTrainingSurveyInDto();

        this.mockMvc.perform(
            post(ApiUrl.SURVEY_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(assignTrainingSurveyInDto)))
            .andDo(print())
            .andExpect(status().isOk());
    }

}
