package com.athleticspot.training.domain;

/**
 * @author Tomasz Kasprzycki
 *         <p>
 *         https://www.interexchange.org/articles/career-training-usa/2012/05/24/imperial-vs-metric-system/
 */
public enum MeasureSystemType {

    METRIC("metric"), //kg, km, cm ...
    IMPERIAL("imperial"); //miles, yards, pounds ...

    private String value;

    MeasureSystemType(String measureType) {
        this.value = measureType;
    }

    @Override
    public String toString() {
        return "MeasureSystemType{" +
            "value='" + value + '\'' +
            '}';
    }
}
