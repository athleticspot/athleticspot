package com.athleticspot.tracker;

import com.athleticspot.tracker.domain.model.GenericSportActivityRepository;
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
    GenericSportActivityRepository genericSportActivityRepository;

    public static void main(String[] args) {
        SpringApplication.run(TrackerApplication.class, args);

    }

    @PostConstruct
    public void run() {
        genericSportActivityRepository.deleteAll();
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
//    ManualSportActivityRepository sportActivityRepository(){
//        return new ManualSportActivityJpaRepositoryImpl();
//    }
//
//    @Bean
//    ApplicationEvents applicationEvents(){
//        return new ApplicationEventsImpl();
//    }
}
