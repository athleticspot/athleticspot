package com.athleticspot.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggingConfiguration {

//    private final Logger log = LoggerFactory.getLogger(LoggingConfiguration.class);
//
//    private LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
//
//    private final String appName;
//
//    private final String serverPort;
//
//    private final String instanceId;
//
//    private final JHipsterProperties jHipsterProperties;
//
//    public LoggingConfiguration(@Value("${spring.application.name}") String appName, @Value("${server.port}") String serverPort,
//                                @Value("${eureka.instance.instanceId}") String instanceId, JHipsterProperties jHipsterProperties) {
//        this.appName = appName;
//        this.serverPort = serverPort;
//        this.instanceId = instanceId;
//        this.jHipsterProperties = jHipsterProperties;
//        if (jHipsterProperties.getLogging().getLogstash().isEnabled()) {
//            addLogstashAppender(context);
//
//            // Add context listener
//            LogbackLoggerContextListener loggerContextListener = new LogbackLoggerContextListener();
//            loggerContextListener.setContext(context);
//            context.addListener(loggerContextListener);
//        }
//    }
//
//    public void addLogstashAppender(LoggerContext context) {
//        log.info("Initializing Logstash logging");
//
//        LogstashSocketAppender logstashAppender = new LogstashSocketAppender();
//        logstashAppender.setName("LOGSTASH");
//        logstashAppender.setContext(context);
//        String customFields = "{\"app_name\":\"" + appName + "\",\"app_port\":\"" + serverPort + "\"}";
//
//        // Set the Logstash appender config from JHipster properties
//        logstashAppender.setSyslogHost(jHipsterProperties.getLogging().getLogstash().getHost());
//        logstashAppender.setPort(jHipsterProperties.getLogging().getLogstash().getPort());
//        logstashAppender.setCustomFields(customFields);
//
//        // Limit the maximum length of the forwarded stacktrace so that it won't exceed the 8KB UDP limit of logstash
//        ShortenedThrowableConverter throwableConverter = new ShortenedThrowableConverter();
//        throwableConverter.setMaxLength(7500);
//        throwableConverter.setRootCauseFirst(true);
//        logstashAppender.setThrowableConverter(throwableConverter);
//
//        logstashAppender.start();
//
//        // Wrap the appender in an Async appender for performance
//        AsyncAppender asyncLogstashAppender = new AsyncAppender();
//        asyncLogstashAppender.setContext(context);
//        asyncLogstashAppender.setName("ASYNC_LOGSTASH");
//        asyncLogstashAppender.setQueueSize(jHipsterProperties.getLogging().getLogstash().getQueueSize());
//        asyncLogstashAppender.addAppender(logstashAppender);
//        asyncLogstashAppender.start();
//
//        context.getLogger("ROOT").addAppender(asyncLogstashAppender);
//    }
//
//    /**
//     * Logback configuration is achieved by configuration file and API.
//     * When configuration file change is detected, the configuration is reset.
//     * This listener ensures that the programmatic configuration is also re-applied after reset.
//     */
//    class LogbackLoggerContextListener extends ContextAwareBase implements LoggerContextListener {
//
//        @Override
//        public boolean isResetResistant() {
//            return true;
//        }
//
//        @Override
//        public void onStart(LoggerContext context) {
//            addLogstashAppender(context);
//        }
//
//        @Override
//        public void onReset(LoggerContext context) {
//            addLogstashAppender(context);
//        }
//
//        @Override
//        public void onStop(LoggerContext context) {
//            // Nothing to do.
//        }
//
//        @Override
//        public void onLevelChange(ch.qos.logback.classic.Logger logger, Level level) {
//            // Nothing to do.
//        }
//    }

}
