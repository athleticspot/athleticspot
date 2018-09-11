import {ActivityDetailsModel} from "./activity-details.model";

export class ActivityModel {

    activityDetails : ActivityDetailsModel
    source = "MANUAL";
    title = "title";
    description = "description";
    type = "running";
    duration = "25";
    distance = "10";
    unit = "km";
    maxSpeed = "";
    meanSpeed = "";
    dateTime : String;


}
