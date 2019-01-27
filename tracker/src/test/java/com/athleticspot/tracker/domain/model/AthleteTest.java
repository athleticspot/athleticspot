package com.athleticspot.tracker.domain.model;

import com.athleticspot.tracker.domain.graph.Athlete;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.UUID;

/**
 * @author Tomasz Kasprzycki
 */
public class AthleteTest {


    @Test
    public void whenUnfallowThenRelationshipIsGone(){
        //given
        Athlete tomek = new Athlete("tomkasp", UUID.randomUUID().toString(), "Tom Kasp");
        Athlete olek = new Athlete("olek", UUID.randomUUID().toString(), "Olo Kasp");
        ReflectionTestUtils.setField(tomek, "id", 1L);
        ReflectionTestUtils.setField(olek, "id", 2L);

        tomek.fallow(olek);
        Assertions.assertThat(tomek.getFriends()).contains(olek);

        //when
        tomek.unfallow(olek);


        //then
        Assertions.assertThat(tomek.getFriends()).hasSize(0);
    }
}
