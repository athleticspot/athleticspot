package com.athleticspot.config;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Tomasz Kasprzycki
 */
@Configuration
@EnableTransactionManagement
@ComponentScan("com.athleticspot.tracker.domain.graph")
@EnableNeo4jRepositories(
    sessionFactoryRef = "neo4jSessionFactory",
    transactionManagerRef = "graphTransactionManager",
    basePackages = "com.athleticspot.tracker.domain.graph")
public class Neo4jDatabaseConfiguration {

    @Bean(name = "neo4jSessionFactory")
    public SessionFactory graphSessionFactory(org.neo4j.ogm.config.Configuration configuration) {
        return new SessionFactory(configuration,
            "com.athleticspot.tracker.domain.graph");
    }

    @Bean
    public org.neo4j.ogm.config.Configuration configuration(Neo4jProperties properties) {
        return properties.createConfiguration();
    }

    @Bean(name = "graphTransactionManager")
    public Neo4jTransactionManager transactionManager(SessionFactory sessionFactory,
                                                      ObjectProvider<TransactionManagerCustomizers> transactionManagerCustomizers) {
        return customize(new Neo4jTransactionManager(sessionFactory),
            transactionManagerCustomizers.getIfAvailable());
    }

    private Neo4jTransactionManager customize(Neo4jTransactionManager transactionManager,
                                              TransactionManagerCustomizers customizers) {
        if (customizers != null) {
            customizers.customize(transactionManager);
        }
        return transactionManager;
    }

//    @Bean
//    public org.neo4j.ogm.config.Configuration configuration() {
//        org.neo4j.ogm.config.Configuration configuration =
//            new org.neo4j.ogm.config.Configuration();
//
//        configuration.driverConfiguration()
//            .setURI("http://neo4j:neo4j@localhost:7474");
//        return configuration;
//    }

}
