package com.athleticspot.tracker.infrastracture.service;

import com.athleticspot.common.SecurityUtils;
import com.athleticspot.common.domain.model.MetricSystemType;
import com.athleticspot.tracker.domain.graph.Athlete;
import com.athleticspot.tracker.domain.graph.GraphAthleteRepository;
import com.athleticspot.tracker.domain.model.MeasureSystemProvider;
import com.athleticspot.tracker.domain.model.SurveyInfo;
import com.athleticspot.tracker.domain.model.SurveyInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import systems.uom.common.USCustomary;
import tech.units.indriya.unit.Units;

import javax.measure.Unit;
import javax.measure.quantity.Length;

import static tec.units.indriya.unit.MetricPrefix.KILO;

/**
 * @author Tomasz Kasprzycki
 */
@Service
public class MeasureSystemProviderImpl implements MeasureSystemProvider {

    private final SurveyInfoRepository surveyInfoRepository;

    private final GraphAthleteRepository graphAthleteRepository;

    @Autowired
    public MeasureSystemProviderImpl(SurveyInfoRepository surveyInfoRepository,
                                     GraphAthleteRepository graphAthleteRepository) {
        this.surveyInfoRepository = surveyInfoRepository;
        this.graphAthleteRepository = graphAthleteRepository;
    }

    @Override
    public Unit<Length> getDistanceUnit() {
        final String currentUserLogin = SecurityUtils.getCurrentUserLogin();
        final Athlete athlete = graphAthleteRepository.findByName(currentUserLogin).orElseThrow(() -> new IllegalStateException());
        final SurveyInfo surveyInfo = surveyInfoRepository.findByAthleteId(athlete.getAthleteUUID()).orElseThrow(() -> new IllegalStateException());

        if(MetricSystemType.METRIC.equals(surveyInfo.getMetricSystemType())) {
            return KILO(Units.METRE);
        }
        return USCustomary.MILE;
    }
}
