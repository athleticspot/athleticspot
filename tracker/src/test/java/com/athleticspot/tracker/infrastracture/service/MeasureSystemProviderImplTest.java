package com.athleticspot.tracker.infrastracture.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import systems.uom.common.USCustomary;
import tech.units.indriya.unit.Units;

import javax.measure.MetricPrefix;

/**
 * @author Tomasz Kasprzycki
 */
public class MeasureSystemProviderImplTest {

    @Test
    public void unitNameFetchTest(){
        Assertions.assertThat(MetricPrefix.KILO(Units.METRE).toString()).isEqualTo("km");
    }

    @Test
    public void verifyMilesUnitSymbol(){
        Assertions.assertThat(USCustomary.MILE.toString()).isEqualTo("mi");
    }

}
