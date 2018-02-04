package com.athleticspot.training.infrastracture.dto.in;

import com.athleticspot.training.domain.MetricSystemType;

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
