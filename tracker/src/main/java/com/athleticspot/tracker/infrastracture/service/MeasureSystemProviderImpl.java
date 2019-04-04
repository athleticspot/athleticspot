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

import javax.measure.MetricPrefix;
import javax.measure.Unit;
import javax.measure.quantity.Length;

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
    public MetricSystemType getUserMetricSystemType(){
        return getMetricSystemType();
    }

    @Override
    public Unit<Length> getUserDistanceUnit() {
        final MetricSystemType metricSystemType = getMetricSystemType();

        if(MetricSystemType.METRIC.equals(metricSystemType)) {
            return MetricPrefix.KILO(Units.METRE);
        }
        return USCustomary.MILE;
    }

    private MetricSystemType getMetricSystemType() {
        final String currentUserLogin = SecurityUtils.getCurrentUserLogin();
        final Athlete athlete = graphAthleteRepository.findByName(currentUserLogin).orElseThrow(() -> new IllegalStateException("There are no athlete withlogin: " + currentUserLogin));
        final SurveyInfo surveyInfo = surveyInfoRepository.findByAthleteId(athlete.getAthleteUUID()).orElseThrow(() -> new IllegalStateException("There are no survey info for user uuid: " + athlete.getAthleteUUID()));
        return surveyInfo.getMetricSystemType();
    }

}
