package com.athleticspot.tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Tomasz Kasprzycki
 */
@SpringBootApplication
//@EntityScan(value = {"com.athleticspot.tracker.domain.model"})
// @EnableJpaRepositories
public class TrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrackerApplication.class, args);
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
