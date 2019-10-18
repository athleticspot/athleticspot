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

    private SportActivityOutDto assembleSportActivity(SportActivity sportActivity) {
        return new SportActivityOutDto()
            .setId(sportActivity.getId().toString())
            .setExternalId(sportActivity.getExternalId())
            .setUsername(sportActivity.getFirstAndLastName())
            .setUserUuid(sportActivity.getUserUuid())
            .setTrackerSource(sportActivity.getTrackerSource())
            .setSportActivityType(sportActivity.getSportActivityType())
            .setTitle(sportActivity.getTitle())
            .setDescription(sportActivity.getDescription())
            .setDistance(sportActivity.getDistance(measureSystemProvider.getUserDistanceUnit()))
            .setDistanceUnits(measureSystemProvider.getUserDistanceUnit().toString())
            .setMovingTime(QuantitiesConverter.convertSecondsToTime(sportActivity.getMovingTime()))
            .setElapsedTime(QuantitiesConverter.convertSecondsToTime(sportActivity.getElapsedTime()))
            .setElapsedTimeInSeconds(sportActivity.getElapsedTime())
            .setStartDate(sportActivity.getStartDate())
            .setAverageSpeed(sportActivity.getAverageSpeed())
            .setMaxSpeed(sportActivity.getMaxSpeed())
            .setAverageTemp(sportActivity.getAverageTemp())
            .setCalories(sportActivity.getCalories())
            .setCoordinates(createCoordinatesFrom(sportActivity.getSummaryPolyline()))
            .setPace()
            .setActivityUrl(sportActivity.activityUrl());
    }

    private List<LatLng> createCoordinatesFrom(String summaryPolyline) {
        if (Objects.nonNull(summaryPolyline)) {
            return PolylineEncoding.decode(summaryPolyline);
        }
        return Lists.newArrayList();
    }

}
