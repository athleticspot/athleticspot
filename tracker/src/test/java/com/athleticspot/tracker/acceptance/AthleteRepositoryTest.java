package com.athleticspot.tracker.acceptance;

import com.athleticspot.tracker.domain.graph.Athlete;
import com.athleticspot.tracker.domain.graph.GraphAthleteRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
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
        Athlete athlete = new Athlete("Aleksander", UUID.randomUUID().toString(), "");
        graphAthleteRepository.save(athlete);
        Assertions.assertThat(graphAthleteRepository.findAll()).hasSize(1);
    }

    @Test
    public void fallowAthleteTest(){
        final String tomekAthleteUuid = UUID.randomUUID().toString();
        Athlete tomek = new Athlete("Tomek", tomekAthleteUuid, "Tom Kasp");
        final String olekAthleteUuid = UUID.randomUUID().toString();
        Athlete olek = new Athlete("Olek", olekAthleteUuid, "Olek Kasp");
        tomek.fallow(olek);

        graphAthleteRepository.save(tomek);
        graphAthleteRepository.save(olek);

        Assertions.assertThat(graphAthleteRepository.findAll()).hasSize(2);
        Assertions.assertThat(graphAthleteRepository.findAllFallowedAthletes(tomekAthleteUuid)).hasSize(1);

    }

    @Test
    public void findAthleteByNameTest(){
        Athlete tomek = new Athlete("Tomek", UUID.randomUUID().toString(), "Tom Kasp");
        graphAthleteRepository.save(tomek);

        final Optional<Athlete> byName = graphAthleteRepository.findByName(tomek.getName());
        Assertions.assertThat(byName.get()).isEqualTo(tomek);

    }

    @Test
    public void findAthleteByName(){
        Athlete tomek = new Athlete("Tomek", UUID.randomUUID().toString(), "Tom Kasp");
        Athlete olek = new Athlete("Olek", UUID.randomUUID().toString(), "Olek Kasp");
        graphAthleteRepository.save(tomek);
        graphAthleteRepository.save(olek);

        final Page<Athlete> page = graphAthleteRepository.findAthletes("Kasp", PageRequest.of(0,10));
        Assertions.assertThat(page.getContent()).hasSize(2);
    }


    @Test
    public void findAthleteByNameWithoutMe(){
        Athlete tomek = new Athlete("Tomek", UUID.randomUUID().toString(), "Tom Kasp");
        Athlete olek = new Athlete("Olek", UUID.randomUUID().toString(), "Olek Kasp");
        graphAthleteRepository.save(tomek);
        graphAthleteRepository.save(olek);

        final Page<Athlete> page = graphAthleteRepository.findAthletesWithoutMe("kasp", tomek.getName(), PageRequest.of(0,10));
        Assertions.assertThat(page.getContent()).hasSize(1).contains(olek);
    }

}
