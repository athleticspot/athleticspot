import {ActivityDetailsModel} from "./activity-details.model";
import moment = require("moment");

export class ActivityModel {

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

    // public assignDetails(activityDetails: ActivityDetailsModel) {
    //     this.title = activityDetails.title;
    //     this.description = activityDetails.description;
    //     this.type = activityDetails.type;
    //     this.duration = activityDetails.duration;
    //     this.distance = activityDetails.distance;
    //     this.unit = activityDetails.unit;
    //     this.maxSpeed = activityDetails.maxSpeed;
    //     this.meanSpeed = activityDetails.meanSpeed;
    // }

}
