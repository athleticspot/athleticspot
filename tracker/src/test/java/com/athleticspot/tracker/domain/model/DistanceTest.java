package com.athleticspot.tracker.domain.model;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import tec.units.ri.quantity.Quantities;

import javax.measure.Quantity;
import javax.measure.quantity.Length;

import static tec.units.ri.unit.Units.METRE;


/**
 * @author Tomasz Kasprzycki
 */
public class DistanceTest {

    @Test
    public void testSimpleDistanceDefinition(){
        final double distanceInMeters = 123.22;
        final Quantity<Length> distance = createDistance(distanceInMeters);

        Assertions.assertThat(distance.getUnit()).isEqualTo(METRE);

    }

    private Quantity<Length> createDistance(Double distanceInMetres){
        final Quantity<Length> quantity = Quantities.getQuantity(distanceInMetres, METRE);
        return quantity;
    }
}
