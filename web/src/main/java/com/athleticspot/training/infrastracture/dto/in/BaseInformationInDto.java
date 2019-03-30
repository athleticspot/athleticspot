package com.athleticspot.training.infrastracture.dto.in;

import com.athleticspot.common.domain.model.MetricSystemType;

import java.time.LocalDate;

/**
 * @author Tomasz Kasprzycki
 */
public class BaseInformationInDto {

    private LocalDate birthDay;

    private Double bodyMass;

    private Double height;

    private MetricSystemType metricSystemType;

}
