import {ActivityDetailsModel} from "./activity-details.model";

export class SportActivityModel {

    activityDetails: ActivityDetailsModel;
    sportActivityIdentifier: String;
    source: String;
    type = "running";
    duration = "25";
    unit = "km";
    meanSpeed = "";


     id: String;
     trackerSource: String;
     sportActivityType: String;
     title: String;
     description: String;
     distance: Number;
     movingTime; Number;
     elapsedTime: Number;
     startDate: String;  //Sting type is set on pourpose in order to not use js Date
     averageSpeed: Number;
     maxSpeed: Number;
     averageTemp: Number;
     calories: Number;




    // constructor(sportActivityIdentifier: String,
    //             source: String,
    //             sportActivityDetails: ActivityDetailsModel,
    //             startDate: Date) {
    //     this.startDate = moment(startDate).format("YYYY-MM-DD HH:mm:ss");
    //     this.source = source;
    //     this.sportActivityIdentifier = sportActivityIdentifier;
    //     this.activityDetails = sportActivityDetails;
    //
    // }





}
