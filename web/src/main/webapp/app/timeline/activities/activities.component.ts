import {Component, OnInit} from "@angular/core";
import {ActivitiesDataservice} from "./activities.dataservice";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivityModel} from "./activity.model";
import * as moment from 'moment';
import {Response} from '@angular/http';
import {forEach} from "../../../../../../node_modules/@angular/router/src/utils/collection";
import {ActivityDetailsModel} from "./activity-details.model";
import {ToasterService} from "angular2-toaster";


@Component({
    selector: 'athleticspot-activities',
    templateUrl: './activities.component.html'
})
export class ActivitiesComponent implements OnInit {

    private addActivityForm: FormGroup;
    private toasterService: ToasterService;
    private activities = [];


    constructor(private activityDataservice: ActivitiesDataservice, toasterService: ToasterService) {
        this.toasterService = toasterService;
    }

    ngOnInit(): void {
        this.addActivityForm = new FormGroup({
            'title': new FormControl(null, Validators.required),
            'description': new FormControl(),
            'type': new FormControl("RUN", Validators.required),
            'duration': new FormGroup({
                "hours": new FormControl(0),
                "minutes": new FormControl(0),
                "seconds": new FormControl(0)
            }),
            'distance': new FormControl(),
            'unit': new FormControl("kilometers"),
            'date': new FormControl(new Date()),
            'time': new FormControl("0"),
            'maxSpeed': new FormControl(),
            'meanSpeed': new FormControl()
        });
        this.refreshActivities();
    }

    submitActivity() {

        let activity = this.addActivityForm.value as ActivityModel;
        activity.source = "MANUAL";
        activity.dateTime = moment(this.addActivityForm.get('date').value)
            .startOf('day')
            .add(this.addActivityForm.get('time').value,
                'hours')
            .format("YYYY-MM-DDTHH:mm:ss");

        activity.duration =
            this.addActivityForm.get("duration.hours").value + "," +
            this.addActivityForm.get("duration.minutes").value + "," +
            this.addActivityForm.get("duration.seconds").value;
        console.log(activity);
        this.activityDataservice.createActivity(activity).subscribe(isSuccess => {
            this.refreshActivities();
            this.toasterService.pop('success', 'Activity', 'Activity created successfully');
        }, error => {
            this.toasterService.pop('error', 'Activity', 'Activity create failed');
            console.log(error);
        });
        this.addActivityForm.reset();
    }

    private refreshActivities() {
        this.activities = [];
        this.activityDataservice.fetchActivity().subscribe((activities: any[]) =>
            activities.forEach(sportActivity => {
                this.activities.push(new ActivityModel(
                    sportActivity.sportyActivityIdentifier,
                    sportActivity.source,
                    sportActivity.details
                ));
            })
        );
    }
}
