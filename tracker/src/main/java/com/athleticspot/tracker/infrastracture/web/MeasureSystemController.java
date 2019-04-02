package com.athleticspot.tracker.infrastracture.web;

import com.athleticspot.tracker.domain.model.MeasureSystemProvider;
import com.athleticspot.tracker.infrastracture.web.dto.out.MeasureSystemOutDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tomasz Kasprzycki
 */
@RestController
@RequestMapping(value = SportTrackersApiUrl.MEASURE_SYSTEM_URL)
public class MeasureSystemController {

    private final MeasureSystemProvider measureSystemProvider;

    @Autowired
    public MeasureSystemController(MeasureSystemProvider measureSystemProvider) {
        this.measureSystemProvider = measureSystemProvider;
    }

    @GetMapping
    public MeasureSystemOutDto retrieveMeasureSystemType() {
        return new MeasureSystemOutDto(measureSystemProvider.getUserMetricSystemType().toString());
    }
}
