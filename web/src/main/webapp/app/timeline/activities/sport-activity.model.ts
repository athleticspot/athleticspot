export class SportActivityModel {

    duration: String;
    unit = "km";
    time = "0";


    id: String;
    trackerSource: String;
    sportActivityType: String;
    title: String;
    description: String;
    distance: Number;
    movingTime;
    Number;
    elapsedTime: Number;
    startDate: String;  //Sting type is set on pourpose in order to not use js Date
    averageSpeed: Number;
    maxSpeed: Number;
    averageTemp: Number;
    calories: Number;
}
