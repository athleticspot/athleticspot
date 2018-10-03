package com.athleticspot.tracker;

import com.athleticspot.tracker.domain.model.GeneralSportActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

/**
 * @author Tomasz Kasprzycki
 */
@SpringBootApplication
//@EntityScan(value = {"com.athleticspot.tracker.domain.model"})
// @EnableJpaRepositories
public class TrackerApplication {

    @Autowired
    GeneralSportActivityRepository stravaSportActivity;

    public static void main(String[] args) {
        SpringApplication.run(TrackerApplication.class, args);

    }

    @PostConstruct
    public void run() {

        stravaSportActivity.deleteAll();
    }

//
//    @Bean
//    TimelineService timelineService(){
//        return new TimelineServiceImpl(
//            timelineRepository(),
//            userRepository(),
//            sportActivityRepository(),
//            applicationEvents()
//
//        );
//    }
//
//    @Bean
//    TimelineRepository timelineRepository(){
//        return new TimelineRepositoryJpaImpl();
//    }
//
////    @Bean
//    UserRepository userRepository(){
//        return new UserRepositoryJpaImpl();
//    }
//
//    @Bean
//    SportActivityRepository sportActivityRepository(){
//        return new SportActivityJpaRepositoryImpl();
//    }
//
//    @Bean
//    ApplicationEvents applicationEvents(){
//        return new ApplicationEventsImpl();
//    }
}
