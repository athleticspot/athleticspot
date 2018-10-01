package com.athleticspot.tracker.infrastracture;

import org.apache.camel.component.quartz2.QuartzComponent;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Properties;

/**
 * @author Tomasz Kasprzycki
 */
@Configuration
public class QuartzConfiguration {

    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }

    @Bean
    public QuartzComponent quartz() throws IOException {
        QuartzComponent quartzComponent = new QuartzComponent();
        quartzComponent.setProperties(quartzProperties());
        return quartzComponent;
    }
}
