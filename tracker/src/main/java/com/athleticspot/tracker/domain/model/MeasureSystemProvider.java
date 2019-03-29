package com.athleticspot.tracker.domain.model;

import javax.measure.Unit;
import javax.measure.quantity.Length;

/**
 * @author Tomasz Kasprzycki
 */
public interface MeasureSystemProvider {

    Unit<Length> getDistanceUnit();
}
