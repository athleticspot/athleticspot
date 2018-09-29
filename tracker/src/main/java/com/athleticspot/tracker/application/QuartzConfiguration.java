package com.athleticspot.tracker.application;

import org.apache.camel.component.quartz2.QuartzComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Tomasz Kasprzycki
 */
@Configuration
public class QuartzConfiguration {

    @Bean
    public QuartzComponent quartz2(){
        return new QuartzComponent();
    }
}
