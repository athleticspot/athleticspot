package com.athleticspot.tracker.infrastracture.service;

import com.athleticspot.common.domain.model.MetricSystemType;
import com.athleticspot.tracker.domain.graph.Athlete;
import com.athleticspot.tracker.domain.graph.GraphAthleteRepository;
import com.athleticspot.tracker.domain.model.MeasureSystemProvider;
import com.athleticspot.tracker.domain.model.SurveyInfo;
import com.athleticspot.tracker.domain.model.SurveyInfoRepository;
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
import java.util.UUID;

/**
 * @author Tomasz Kasprzycki
 */
@RunWith(MockitoJUnitRunner.class)
public class MeasureSystemProviderImplTest {

    @Mock
    private SurveyInfoRepository surveyInfoRepository;

    @Mock
    private GraphAthleteRepository graphAthleteRepository;

    private MeasureSystemProvider measureSystemProvider;

    private final String userId = UUID.randomUUID().toString();

    @Before
    public void setUp() {
        measureSystemProvider = new MeasureSystemProviderImpl(surveyInfoRepository, graphAthleteRepository);
    }

    @Test
    public void when_user_has_metric_system_assigned_then_its_returned_and_units_are_km() {
        //given
        Mockito.when(graphAthleteRepository.findByName(ArgumentMatchers.any())).thenReturn(createAthlete());
        Mockito.when(surveyInfoRepository.findByUserId(userId)).thenReturn(createSurveyInfo());

        //when
        final MetricSystemType metricSystemType = measureSystemProvider.getUserMetricSystemType();
        final Unit<Length> lengthUnit = measureSystemProvider.getUserDistanceUnit();

        //then
        Assertions.assertThat(metricSystemType).isEqualByComparingTo(MetricSystemType.METRIC);
        Assertions.assertThat(lengthUnit).isEqualTo(MetricPrefix.KILO(Units.METRE));
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

    private Optional<Athlete> createAthlete() {
        return Optional.of(new Athlete("tomek", userId, "Tom Kasp"));
    }

    private Optional<SurveyInfo> createSurveyInfo() {
        final SurveyInfo result = new SurveyInfo();
        ReflectionTestUtils.setField(result, "metricSystemType", MetricSystemType.METRIC);
        return Optional.of(result);
    }
}
