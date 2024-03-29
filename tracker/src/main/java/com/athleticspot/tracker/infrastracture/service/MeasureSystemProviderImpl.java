package com.athleticspot.tracker.infrastracture.service;

import com.athleticspot.common.SecurityUtils;
import com.athleticspot.common.domain.model.MetricSystemType;
import com.athleticspot.tracker.domain.model.MeasureSystemProvider;
import com.athleticspot.tracker.domain.model.TrackerUser;
import com.athleticspot.tracker.domain.model.TrackerUserRepository;
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

    private final TrackerUserRepository trackerUserRepository;

    @Autowired
    public MeasureSystemProviderImpl(TrackerUserRepository trackerUserRepository) {
        this.trackerUserRepository = trackerUserRepository;
    }

    @Override
    public MetricSystemType getUserMetricSystemType() {
        return getMetricSystemType();
    }

    @Override
    public Unit<Length> getUserDistanceUnit() {
        final MetricSystemType metricSystemType = getMetricSystemType();

        if (MetricSystemType.METRIC.equals(metricSystemType)) {
            return MetricPrefix.KILO(Units.METRE);
        }
        return USCustomary.MILE;
    }

    private MetricSystemType getMetricSystemType() {
        final String currentUserLogin = SecurityUtils.getCurrentUserLogin();
        return trackerUserRepository.findByLogin(currentUserLogin).map(TrackerUser::getMetricSystemType).orElse(MetricSystemType.METRIC);
    }

}
