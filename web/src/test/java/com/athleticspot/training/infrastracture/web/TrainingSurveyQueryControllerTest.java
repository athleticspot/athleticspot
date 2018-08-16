package com.athleticspot.training.infrastracture.web;

import com.athleticspot.AthleticspotApp;
import com.athleticspot.common.domain.User;
import com.athleticspot.service.UserService;
import com.athleticspot.training.domain.AthleteRepository;
import com.athleticspot.training.domain.trainingsurvey.TrainingSurveyProvider;
import com.athleticspot.training.domain.trainingsurvey.TrainingSurveyRepository;
import com.athleticspot.training.infrastracture.dto.out.TrainingSurveyOutDtoAssembler;
import com.athleticspot.training.infrastracture.persistnce.TrainingSurveyProviderImpl;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Tomasz Kasprzycki
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AthleticspotApp.class)
public class TrainingSurveyQueryControllerTest {

    @Mock
    TrainingSurveyProvider trainingSurveyProvider;

    @Mock
    private UserService mockUserService;

    @Autowired
    private HttpMessageConverter[] httpMessageConverters;

    @Autowired
    TrainingSurveyRepository trainingSurveyRepository;

    @Autowired
    TrainingSurveyOutDtoAssembler trainingSurveyOutDtoAssembler;

    @Autowired
    AthleteRepository athleteRepository;



    private MockMvc restMvc;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        trainingSurveyProvider = new TrainingSurveyProviderImpl(
            mockUserService, athleteRepository, trainingSurveyRepository
        );
        TrainingSurveyQueryController trainingSurveyQueryController =
            new TrainingSurveyQueryController(trainingSurveyProvider, trainingSurveyOutDtoAssembler);
        this.restMvc = MockMvcBuilders.standaloneSetup(trainingSurveyQueryController)
            .setMessageConverters(httpMessageConverters)
            .build();
        User user = TestUtil.createFakeUser();
        when(mockUserService.getUserWithAuthorities()).thenReturn(user);
    }


    @Test
    public void testFetchingTrainingSurvey() throws Exception {

        restMvc.perform(get(ApiUrl.SURVEY_URL)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
        //todo add content check
    }


    @Test
    public void testNotAssignedTrainingSurvey() throws Exception {

        restMvc.perform(get(ApiUrl.SURVEY_URL)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andExpect(content().string(""));
    }


}
