import {Component, OnInit} from "@angular/core";
import {ActivitiesDataservice} from "./activities.dataservice";
import {FormControl, FormGroup} from "@angular/forms";
import {ActivityModel} from "./activity.model";

@Component({
    selector: 'athleticspot-activities',
    templateUrl: './activities.component.html'
})
export class ActivitiesComponent implements OnInit {

    private addActivityForm: FormGroup;


    constructor(private activityDataservice: ActivitiesDataservice) {
    }

    ngOnInit(): void {
        this.addActivityForm = new FormGroup({
            'title': new FormControl(),
            'description': new FormControl(),
            'type': new FormControl(),
            'duration': new FormControl(),
            'distance': new FormControl(),
            'maxSpeed': new FormControl(),
            'meanSpeed': new FormControl()
        });
        // this.activityDataservice.fetchActivity().subscribe(value =>
        //     console.log(value)
        // );
    }

    submitActivity() {
        let activity = this.addActivityForm.value as ActivityModel;
        activity.source = "MANUAL";
        activity.dateTime = new Date();
        activity.units = 'metric';
        console.log(activity);
        this.activityDataservice.createActivity(activity).subscribe(isSuccess => {
            console.log(isSuccess);
        }, error => {
            console.log(error);
        });
    }
}

//
// public _source = "MANUAL",
//     public _title = "title",
//     public _description = "description",
//     public _type = "running",
//     public _duration = "25",
//     public _distance = "10",
//     public _units = "km",
//     public _maxSpeed = "33",
//     public _meanSpeed = "15",
//     public _dateTime = new Date())
