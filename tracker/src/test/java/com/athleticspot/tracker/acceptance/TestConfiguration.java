package com.athleticspot.tracker.acceptance;

import com.athleticspot.tracker.application.ApplicationEvents;
import com.athleticspot.tracker.application.ApplicationEventsImpl;
import com.athleticspot.tracker.application.TimelineService;
import com.athleticspot.tracker.application.impl.TimelineServiceImpl;
import com.athleticspot.tracker.domain.model.SportActivityRepository;
import com.athleticspot.tracker.domain.model.TimelineRepository;
import com.athleticspot.tracker.domain.model.UserRepository;
import com.athleticspot.tracker.infrastracture.persistance.jpa.SportActivityJpaRepositoryImpl;
import com.athleticspot.tracker.infrastracture.persistance.jpa.TimelineRepositoryJpaImpl;
import com.athleticspot.tracker.infrastracture.persistance.jpa.UserRepositoryJpaImpl;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Tomasz Kasprzycki
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
@EntityScan(value = {"com.athleticspot.tracker.domain.model"})
public class TestConfiguration {

    @Bean
    TimelineService timelineService(){
        return new TimelineServiceImpl(
            timelineRepository(),
            userRepository(),
            sportActivityRepository(),
            applicationEvents()

        );
    }

    @Bean
    TimelineRepository timelineRepository(){
        return new TimelineRepositoryJpaImpl();
    }

    @Bean
    UserRepository userRepository(){
        return new UserRepositoryJpaImpl();
    }

    @Bean
    SportActivityRepository sportActivityRepository(){
        return new SportActivityJpaRepositoryImpl();
    }

    @Bean
    ApplicationEvents applicationEvents(){
        return new ApplicationEventsImpl();
    }
}
