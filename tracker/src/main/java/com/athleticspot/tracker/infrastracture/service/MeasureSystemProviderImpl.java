package com.athleticspot.tracker.infrastracture.service;

import com.athleticspot.tracker.domain.MeasureSystemProvider;
import org.springframework.stereotype.Service;
import tech.units.indriya.unit.Units;

import javax.measure.Unit;
import javax.measure.quantity.Length;

import static tec.units.indriya.unit.MetricPrefix.KILO;

/**
 * @author Tomasz Kasprzycki
 */
@Service
public class MeasureSystemProviderImpl implements MeasureSystemProvider {

    @Override
    public Unit<Length> getDistanceUnit() {
        return KILO(Units.METRE);
    }
}
