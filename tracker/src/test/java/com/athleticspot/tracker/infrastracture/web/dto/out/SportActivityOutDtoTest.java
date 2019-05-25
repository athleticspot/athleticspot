package com.athleticspot.tracker.infrastracture.web.dto.out;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import systems.uom.common.USCustomary;
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
        ReflectionTestUtils.setField(sportActivityOutDto, "elapsedTimeInSeconds", 3600);
        ReflectionTestUtils.setField(sportActivityOutDto, "units", MetricPrefix.KILO(Units.METRE).toString());
        sportActivityOutDto.setPace();
        Assertions.assertThat(sportActivityOutDto.getPace()).isEqualTo(5.00f);
    }

    @Test
    public void paceCreationHandlingFloatingPointTest(){
        SportActivityOutDto sportActivityOutDto = new SportActivityOutDto();
        ReflectionTestUtils.setField(sportActivityOutDto, "distance", 4.5f);
        ReflectionTestUtils.setField(sportActivityOutDto, "elapsedTimeInSeconds", 3600);
        ReflectionTestUtils.setField(sportActivityOutDto, "units", MetricPrefix.KILO(Units.METRE).toString());
        sportActivityOutDto.setPace();
        Assertions.assertThat(sportActivityOutDto.getPace()).isEqualTo(13.33f);
        Assertions.assertThat(sportActivityOutDto.getFormattedPace()).isEqualTo("13:20");
    }

    @Test
    public void paceConversionFromMetersToMiles(){
        SportActivityOutDto sportActivityOutDto = new SportActivityOutDto();
        ReflectionTestUtils.setField(sportActivityOutDto, "distance", 1.0f);
        ReflectionTestUtils.setField(sportActivityOutDto, "elapsedTimeInSeconds", 3600);
        ReflectionTestUtils.setField(sportActivityOutDto, "units", USCustomary.MILE.toString());
        sportActivityOutDto.setPace();
        Assertions.assertThat(sportActivityOutDto.getPace()).isEqualTo(60.00f);
    }

    @Test
    public void paceCreationForMetersTest(){
        SportActivityOutDto sportActivityOutDto = new SportActivityOutDto();
        ReflectionTestUtils.setField(sportActivityOutDto, "distance", 3020f);
        ReflectionTestUtils.setField(sportActivityOutDto, "elapsedTimeInSeconds", 3600);
        ReflectionTestUtils.setField(sportActivityOutDto, "units", (Units.METRE).toString());
        sportActivityOutDto.setPace();
        Assertions.assertThat(sportActivityOutDto.getPace()).isEqualTo(19.87f);
    }

    @Test
    public void handleDistanceEquals0Test(){
        SportActivityOutDto sportActivityOutDto = new SportActivityOutDto();
        ReflectionTestUtils.setField(sportActivityOutDto, "distance", 0.0f);
        ReflectionTestUtils.setField(sportActivityOutDto, "elapsedTimeInSeconds", 3600);
        ReflectionTestUtils.setField(sportActivityOutDto, "units", USCustomary.MILE.toString());
        sportActivityOutDto.setPace();
        Assertions.assertThat(sportActivityOutDto.getPace()).isEqualTo(0f);
        Assertions.assertThat(sportActivityOutDto.getFormattedPace()).isEqualTo("00:00");
    }

}
