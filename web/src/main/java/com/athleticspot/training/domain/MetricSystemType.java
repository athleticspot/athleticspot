package com.athleticspot.training.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author Tomasz Kasprzycki
 *         <p>
 *         https://www.interexchange.org/articles/career-training-usa/2012/05/24/imperial-vs-metric-system/
 */
public enum MetricSystemType {

    METRIC("metric"), //kg, km, cm ...
    IMPERIAL("imperial"); //miles, yards, pounds ...

    private String value;

    MetricSystemType(String measureType) {
        this.value = measureType;
    }

    @JsonCreator
    public static MetricSystemType create(String value){
        return  MetricSystemType.valueOf(value);
    }

    @Override
    @JsonValue
    public String toString() {
        return value;
    }
}
