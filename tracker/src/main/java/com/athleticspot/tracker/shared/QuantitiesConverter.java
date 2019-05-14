package com.athleticspot.tracker.shared;

import systems.uom.common.USCustomary;
import tech.units.indriya.quantity.Quantities;

import javax.measure.Unit;
import javax.measure.quantity.Length;
import java.text.DecimalFormat;
import java.util.Objects;

import static javax.measure.MetricPrefix.KILO;
import static tech.units.indriya.unit.Units.METRE;

/**
 * @author Tomasz Kasprzycki
 */
public class QuantitiesConverter {

    private static final String DECIMAL_FORMAT = "#.##";

    public static Float convertDistanceToMeters(String units, Float distance) {
        if (KILO(METRE).toString().equals(units)) {
            return Float.parseFloat(new DecimalFormat(DECIMAL_FORMAT)
                .format(Quantities.getQuantity(distance, KILO(METRE)).to(METRE).getValue().floatValue()));
        } else if (USCustomary.MILE.toString().equals(units)) {
            return Float.parseFloat(new DecimalFormat(DECIMAL_FORMAT)
                .format(Quantities.getQuantity(distance, USCustomary.MILE).to(USCustomary.METER).getValue().floatValue()));
        }
        return 0.00f;
    }

    public static Float convertDistanceFromMeters(Unit<Length> units, Float meters) {
        if (USCustomary.MILE.equals(units)) {
            final float convertedValue =
                Quantities.getQuantity(meters, USCustomary.METER).to(USCustomary.MILE).getValue().floatValue();
            return Float.parseFloat(new DecimalFormat(DECIMAL_FORMAT).format(convertedValue));
        }
        return Float.parseFloat(new DecimalFormat(DECIMAL_FORMAT)
            .format(Quantities.getQuantity(meters, METRE).to(units).getValue().floatValue()));
    }

    public static String convertSecondsToTime(Integer input) {
        if (Objects.isNull(input)) {
            return null;
        }
        long hours = (input - input % 3600) / 3600;
        long minutes = (input % 3600 - input % 3600 % 60) / 60;
        long seconds = input % 3600 % 60;
        return hours + "h " + minutes + "m " + seconds + "s";
    }

}
