import {Component, OnInit} from "@angular/core";
import {ActivitiesDataservice} from "./activities.dataservice";
import {FormControl, FormGroup, Validators} from "@angular/forms";
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
            'title': new FormControl(null, Validators.required),
            'description': new FormControl(),
            'type': new FormControl("Running", Validators.required),
            'duration': new FormGroup({
                "hours": new FormControl(0),
                "minutes": new FormControl(0),
                "seconds": new FormControl(0)
            }),
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
        activity.duration = this.addActivityForm.get("duration.hours").value + "," +
            this.addActivityForm.get("duration.minutes").value + "," +
            this.addActivityForm.get("duration.seconds").value;
        console.log(activity);
        this.activityDataservice.createActivity(activity).subscribe(isSuccess => {
            console.log(isSuccess);
        }, error => {
            console.log(error);
        });
    }
}
