package com.athleticspot.training.domain.trainingsurvey;

import com.athleticspot.common.domain.model.MetricSystemType;

/**
 * @author Tomasz Kasprzycki
 */
public class UpdateMetricSystemTypeCommand {

    final private MetricSystemType metricSystemType;

    private UpdateMetricSystemTypeCommand(MetricSystemType metricSystemType) {
        this.metricSystemType = metricSystemType;
    }

    public static UpdateMetricSystemTypeCommand of(MetricSystemType metricSystemType) {
        return new UpdateMetricSystemTypeCommand(metricSystemType);
    }

    public MetricSystemType getMetricSystemType() {
        return metricSystemType;
    }
}
