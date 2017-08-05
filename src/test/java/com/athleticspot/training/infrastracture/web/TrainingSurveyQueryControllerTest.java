package com.athleticspot.training.infrastracture.web;

import com.athleticspot.AthleticspotApp;
import com.athleticspot.training.domain.trainingsurvey.TrainingSurveyProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Tomasz Kasprzycki
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AthleticspotApp.class)
public class TrainingSurveyQueryControllerTest {

    @Autowired
    TrainingSurveyProvider trainingSurveyProvider;

    @Autowired
    private HttpMessageConverter[] httpMessageConverters;

    private MockMvc restMvc;


    @Before
    public void setUp() {
        TrainingSurveyQueryController trainingSurveyQueryController =
            new TrainingSurveyQueryController(trainingSurveyProvider);
        this.restMvc = MockMvcBuilders.standaloneSetup(trainingSurveyQueryController)
            .setMessageConverters(httpMessageConverters)
            .build();
    }


    @Test
    public void testFetchingTrainingSurvey() throws Exception {
        restMvc.perform(get(ApiUrl.SURVEY_URL)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string(""));
    }


}
