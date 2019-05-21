package com.athleticspot.tracker.infrastracture.web.dto.out;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import tech.units.indriya.unit.Units;

import javax.measure.MetricPrefix;

/**
 * @author Tomasz Kasprzycki
 */
public class SportActivityOutDtoTest {

    @Test
    public void paceCreationForKmTest(){
        SportActivityOutDto sportActivityOutDto = new SportActivityOutDto();
        ReflectionTestUtils.setField(sportActivityOutDto, "distance", 12f);
        ReflectionTestUtils.setField(sportActivityOutDto, "elapsedTime", "3600");
        ReflectionTestUtils.setField(sportActivityOutDto, "units", MetricPrefix.KILO(Units.METRE).toString());
        sportActivityOutDto.setPace();
        Assertions.assertThat(sportActivityOutDto.getPace()).isEqualTo(5.00f);
    }

    @Test
    public void paceCreationHandlingFloatingPointTest(){
        SportActivityOutDto sportActivityOutDto = new SportActivityOutDto();
        ReflectionTestUtils.setField(sportActivityOutDto, "distance", 4.5f);
        ReflectionTestUtils.setField(sportActivityOutDto, "elapsedTime", "3600");
        ReflectionTestUtils.setField(sportActivityOutDto, "units", MetricPrefix.KILO(Units.METRE).toString());
        sportActivityOutDto.setPace();
        Assertions.assertThat(sportActivityOutDto.getPace()).isEqualTo(13.33f);
    }

}
