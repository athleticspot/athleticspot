package com.athleticspot.training.infrastracture.web;

import com.athleticspot.AthleticspotApp;
import com.athleticspot.domain.Authority;
import com.athleticspot.domain.User;
import com.athleticspot.security.AuthoritiesConstants;
import com.athleticspot.service.UserService;
import com.athleticspot.training.domain.AthleteRepository;
import com.athleticspot.training.domain.trainingsurvey.TrainingSurveyProvider;
import com.athleticspot.training.domain.trainingsurvey.TrainingSurveyRepository;
import com.athleticspot.training.infrastracture.dto.out.TrainingSurveyOutDtoAssembler;
import com.athleticspot.training.infrastracture.persistnce.TrainingSurveyProviderImpl;
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

import java.util.HashSet;
import java.util.Set;

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
    }


    @Test
    public void testFetchingTrainingSurvey() throws Exception {

        Set<Authority> authorities = new HashSet<>();
        Authority authority = new Authority();
        authority.setName(AuthoritiesConstants.ADMIN);
        authorities.add(authority);

        User user = new User();
        user.setId(3L);
        user.setLogin("test");
        user.setFirstName("john");
        user.setLastName("doe");
        user.setEmail("john.doe@jhipster.com");
        user.setImageUrl("http://placehold.it/50x50");
        user.setLangKey("en");
        user.setAuthorities(authorities);
        when(mockUserService.getUserWithAuthorities()).thenReturn(user);

        restMvc.perform(get(ApiUrl.SURVEY_URL)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string(""));
    }


}
