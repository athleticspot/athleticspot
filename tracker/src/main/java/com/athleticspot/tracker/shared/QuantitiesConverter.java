package com.athleticspot.tracker.shared;

import systems.uom.common.USCustomary;
import tech.units.indriya.quantity.Quantities;

import javax.measure.Unit;
import javax.measure.quantity.Length;
import java.util.Objects;

import static javax.measure.MetricPrefix.KILO;
import static tech.units.indriya.unit.Units.METRE;

/**
 * @author Tomasz Kasprzycki
 */
public class QuantitiesConverter {

    public static Float convertDistanceToMeters(String units, Float distance) {
        if (KILO(METRE).toString().equals(units)) {
            return Quantities.getQuantity(distance, KILO(METRE)).to(METRE).getValue().floatValue();
        } else if (USCustomary.MILE.toString().equals(units)) {
            return Quantities.getQuantity(distance, USCustomary.MILE).to(METRE).getValue().floatValue();
        }
        return 0.00f;
    }

    public static Float convertDistanceFromMeters(Unit<Length> units, Float meters) {
        return Quantities.getQuantity(meters, METRE).to(units).getValue().floatValue();
    }

    public static String convertSecondsToTime(Integer input) {
        if(Objects.isNull(input)){
            return null;
        }
        long hours = (input - input % 3600) / 3600;
        long minutes = (input % 3600 - input % 3600 % 60) / 60;
        long seconds = input % 3600 % 60;
        return hours + "h " + minutes + "m " + seconds + "s";
    }

}
