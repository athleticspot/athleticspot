package com.athleticspot.tracker.infrastracture.assembler;

import com.athleticspot.tracker.domain.graph.SportActivity;
import com.athleticspot.tracker.domain.model.MeasureSystemProvider;
import com.athleticspot.tracker.infrastracture.web.dto.out.SportActivityOutDto;
import com.athleticspot.tracker.shared.QuantitiesConverter;
import com.google.common.collect.Lists;
import com.google.maps.internal.PolylineEncoding;
import com.google.maps.model.LatLng;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * @author Tomasz Kasprzycki
 */
@Component
public class SportActivityAssemblerImpl {

    private final MeasureSystemProvider measureSystemProvider;

    @Autowired
    public SportActivityAssemblerImpl(MeasureSystemProvider measureSystemProvider) {
        this.measureSystemProvider = measureSystemProvider;
    }

    public Page<SportActivityOutDto> pageAssemble(Page<SportActivity> graphSportActivity) {
        return graphSportActivity.map(this::assembleSportActivity);
    }

    private SportActivityOutDto assembleSportActivity(SportActivity graphSportActivity) {
        List<LatLng> coordinates = Lists.newArrayList();
        if (Objects.nonNull(graphSportActivity.getSummaryPolyline())) {
            coordinates = PolylineEncoding.decode(graphSportActivity.getSummaryPolyline());
        }
        return new SportActivityOutDto()
            .setId(graphSportActivity.getId().toString())
            .setUsername(graphSportActivity.getFirstAndLastName())
            .setUserUuid(graphSportActivity.getUserUuid())
            .setTrackerSource(graphSportActivity.getTrackerSource())
            .setSportActivityType(graphSportActivity.getSportActivityType())
            .setTitle(graphSportActivity.getTitle())
            .setDescription(graphSportActivity.getDescription())
            .setDistance(graphSportActivity.getDistance(measureSystemProvider.getUserDistanceUnit()))
            .setDistanceUnits(measureSystemProvider.getUserDistanceUnit().toString())
            .setMovingTime(QuantitiesConverter.convertSecondsToTime(graphSportActivity.getMovingTime()))
            .setElapsedTime(QuantitiesConverter.convertSecondsToTime(graphSportActivity.getElapsedTime()))
            .setStartDate(graphSportActivity.getStartDate())
            .setAverageSpeed(graphSportActivity.getAverageSpeed())
            .setMaxSpeed(graphSportActivity.getMaxSpeed())
            .setAverageTemp(graphSportActivity.getAverageTemp())
            .setCalories(graphSportActivity.getCalories())
            .setCoordinates(coordinates)
            .setPace(measureSystemProvider.getUserDistanceUnit().toString());
    }

}
