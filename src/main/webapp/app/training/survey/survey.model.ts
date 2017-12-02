export enum MetricSystem {
    METRIC,
    IMPERIAL,
}

export interface SurveyModel {


    baseInformation: {
        birthday: Date;
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



    // constructor(public birthDay = new Date(),
    //             public mass = 0.0,
    //             public height = 0,
    //             public metricSystem: MetricSystem.metric,
    //             public healthContraindications = false,
    //             public stressTest = false,
    //             public bloodTest = false,
    //             public hoursOfSleep = false,
    //             public meatAcceptance = false,
    //             public dairyedAcceptance = false,
    //             public allergies = false,
    //             public foodIntolerance = false) {
    //
    // }
}
