import {Component, OnInit} from "@angular/core";
import {ActivitiesDataservice} from "./activities.dataservice";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivityModel} from "./activity.model";
import * as moment from 'moment';

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
            'unit': new FormControl(),
            'date': new FormControl(new Date()),
            'time': new FormControl("00:00AM"),
            'maxSpeed': new FormControl(),
            'meanSpeed': new FormControl()
        });
    }

    submitActivity() {
        let activity = this.addActivityForm.value as ActivityModel;
        activity.source = "MANUAL";
        activity.dateTime = moment(this.addActivityForm.get('date').value)
            .startOf('day')
            .add(this.addActivityForm.get('time').value,
                'hours')
            .toDate();
        activity.duration =
            this.addActivityForm.get("duration.hours").value + "," +
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
