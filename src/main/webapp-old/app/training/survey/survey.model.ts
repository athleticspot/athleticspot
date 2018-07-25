export enum MetricSystem {
    METRIC,
    IMPERIAL,
}

export interface SurveyModel {

    trainingSurveyUuid: String;
    baseInformation: {
        birthDay: Date;
        weight: Number;
        height: Number;
        metricSystemType: String;
    };
    healthInformation: {
        healthContraindications: boolean;
        stressTest: boolean;
        bloodTest: boolean;
        hoursOfSleep: Number;
    };
    nutritionInformation: {
        meatAcceptance:boolean;
        dairyedAcceptance:boolean;
        allergies:boolean;
        foodIntolerance:boolean
    };

}
