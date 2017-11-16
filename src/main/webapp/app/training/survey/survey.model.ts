export enum MetricSystem {
    metric,
    imperial
}

export class SurveyModel {

    constructor(public birthDay = new Date(),
                public mass = 0.0,
                public height = 0,
                public metricSystem: MetricSystem.metric,
                public healthContraindications = false,
                public stressTest = false,
                public bloodTest = false,
                public hoursOfSleep = false,
                public meatAcceptance = false,
                public dairyedAcceptance = false,
                public allergies = false,
                public foodIntolerance = false) {

    }
}
