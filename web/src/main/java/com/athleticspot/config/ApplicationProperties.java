package com.athleticspot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to JHipster.
 * <p>
 * Properties are configured in the application.yml file.
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

    private String url;

    public String getUrl() {
        return url;
    }

    public ApplicationProperties setUrl(String url) {
        this.url = url;
        return this;
    }
}
