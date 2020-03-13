package com.athleticspot.tracker.infrastracture.service;

import com.athleticspot.common.domain.model.MetricSystemType;
import com.athleticspot.tracker.domain.model.MeasureSystemProvider;
import com.athleticspot.tracker.domain.model.TrackerUser;
import com.athleticspot.tracker.domain.model.TrackerUserRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import systems.uom.common.USCustomary;
import tech.units.indriya.unit.Units;

import javax.measure.MetricPrefix;
import javax.measure.Unit;
import javax.measure.quantity.Length;
import java.util.Optional;

/**
 * @author Tomasz Kasprzycki
 */
@RunWith(MockitoJUnitRunner.class)
public class MeasureSystemProviderImplTest {

    @Mock
    private TrackerUserRepository trackerUserRepository;

    private MeasureSystemProvider measureSystemProvider;

    @Before
    public void setUp() {
        measureSystemProvider = new MeasureSystemProviderImpl(trackerUserRepository);
    }

    @Test
    public void when_user_has_metric_system_assigned_then_its_returned_and_units_are_km() {
        //given
        Mockito.when(trackerUserRepository.findByLogin(ArgumentMatchers.any())).thenReturn(createTrackerUser(MetricSystemType.METRIC));

        //when
        final MetricSystemType metricSystemType = measureSystemProvider.getUserMetricSystemType();
        final Unit<Length> lengthUnit = measureSystemProvider.getUserDistanceUnit();

        //then
        Assertions.assertThat(metricSystemType).isEqualByComparingTo(MetricSystemType.METRIC);
        Assertions.assertThat(lengthUnit).isEqualTo(MetricPrefix.KILO(Units.METRE));
    }

    @Test
    public void when_user_has_imperial_system_assigned_then_its_returned_and_units_are_miles() {
        //given
        Mockito.when(trackerUserRepository.findByLogin(ArgumentMatchers.any())).thenReturn(createTrackerUser(MetricSystemType.IMPERIAL));

        //when
        final MetricSystemType metricSystemType = measureSystemProvider.getUserMetricSystemType();
        final Unit<Length> lengthUnit = measureSystemProvider.getUserDistanceUnit();


        //then
        Assertions.assertThat(metricSystemType).isEqualByComparingTo(MetricSystemType.IMPERIAL);
        Assertions.assertThat(lengthUnit).isEqualTo(USCustomary.MILE);

    }

    @Test
    public void unitNameFetchTest() {
        Assertions.assertThat(MetricPrefix.KILO(Units.METRE).toString()).isEqualTo("km");
    }

    @Test
    public void metreNameTest() {
        Assertions.assertThat(Units.METRE.toString()).isEqualTo("m");
    }

    @Test
    public void verifyMilesUnitSymbol() {
        Assertions.assertThat(USCustomary.MILE.toString()).isEqualTo("mi");
    }

    private Optional<TrackerUser> createTrackerUser(MetricSystemType metricSystemType) {
        TrackerUser trackerUser = new TrackerUser();
        ReflectionTestUtils.setField(trackerUser, "metricSystemType", metricSystemType);
        return Optional.of(trackerUser);
    }

}
