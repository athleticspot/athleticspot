import {ActivityDetailsModel} from "./activity-details.model";
import moment = require("moment");

export class SportActivityModel {

    activityDetails: ActivityDetailsModel;
    sportActivityIdentifier: String;
    source: String;
    title: String;
    description = "description";
    type = "running";
    duration = "25";
    distance = "10";
    unit = "km";
    maxSpeed = "";
    meanSpeed = "";
    startDate: String; //Sting type is set on pourpose in order to not use js Date

    constructor(sportActivityIdentifier: String,
                source: String,
                sportActivityDetails: ActivityDetailsModel,
                startDate: Date) {
        this.startDate = moment(startDate).format("YYYY-MM-DD HH:mm:ss");
        this.source = source;
        this.sportActivityIdentifier = sportActivityIdentifier;
        this.activityDetails = sportActivityDetails;

    }
}
