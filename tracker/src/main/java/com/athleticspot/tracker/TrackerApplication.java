package com.athleticspot.tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

/**
 * @author Tomasz Kasprzycki
 */
@SpringBootApplication
public class TrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrackerApplication.class, args);

    }

    @PostConstruct
    public void run() {
//        genericSportActivityRepository.deleteAll();
    }

}
