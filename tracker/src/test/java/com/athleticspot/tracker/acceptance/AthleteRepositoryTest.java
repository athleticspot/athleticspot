package com.athleticspot.tracker.acceptance;

import com.athleticspot.tracker.domain.graph.Athlete;
import com.athleticspot.tracker.domain.graph.GraphAthleteRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

/**
 * @author Tomasz Kasprzycki
 */
@RunWith(SpringRunner.class)
@DataNeo4jTest
public class AthleteRepositoryTest {

    @Autowired
    private GraphAthleteRepository graphAthleteRepository;

    @Test
    public void neo4jSanityTest(){
        Athlete athlete = new Athlete("Aleksander", UUID.randomUUID().toString());
        graphAthleteRepository.save(athlete);
        Assertions.assertThat(graphAthleteRepository.findAll()).hasSize(1);
    }
}
