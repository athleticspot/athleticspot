package com.athleticspot.tracker.domain.model;

import com.athleticspot.common.domain.model.MetricSystemType;

import javax.measure.Unit;
import javax.measure.quantity.Length;

/**
 * @author Tomasz Kasprzycki
 */
public interface MeasureSystemProvider {

    MetricSystemType getUserMetricSystemType();

    Unit<Length> getUserDistanceUnit();
}
