import {Component, OnInit} from "@angular/core";
import {ActivitiesDataservice} from "./activities.dataservice";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import * as moment from 'moment';
import {ToasterService} from "angular2-toaster";
import {StravaDataservice} from "./strava.dataservice";
import {SportActivityModel} from "./sport-activity.model";


@Component({
    selector: 'athleticspot-activities',
    templateUrl: './activities.component.html'
})
export class ActivitiesComponent implements OnInit {

    private addActivityForm: FormGroup;
    private activities = [];
    private showTimeline = false;
    private stravaActivationUrl: String;


    constructor(private activityDataservice: ActivitiesDataservice,
                private stravaDataservice: StravaDataservice,
                private toasterService: ToasterService) {
        this.toasterService = toasterService;
    }

    ngOnInit(): void {
        this.addActivityForm = new FormGroup({
            'title': new FormControl(null, [Validators.required]),
            'description': new FormControl(),
            'type': new FormControl("RUN", [Validators.required]),
            'duration': new FormGroup({
                "hours": new FormControl(0),
                "minutes": new FormControl(0),
                "seconds": new FormControl(0)
            }),
            'distance': new FormControl(),
            'unit': new FormControl("kilometers"),
            'date': new FormControl(new Date()),
            'time': new FormControl("0", [Validators.required]),
            'maxSpeed': new FormControl(),
            'meanSpeed': new FormControl()
        });
        this.refreshActivities();
        this.stravaDataservice.fetchStravaActivationLink()
            .subscribe(value => {
                this.stravaActivationUrl = value;
            })
    }

    submitActivity() {
        console.log(this.addActivityForm);
        if (this.addActivityForm.valid) {
            let activity = this.addActivityForm.value as SportActivityModel;
            activity.source = "MANUAL";
            activity.startDate = moment(this.addActivityForm.get('date').value)
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
            this.refreshAddActivityForm();
        } else {
            this.addActivityForm.markAsDirty({onlySelf: true});
            this.markFormGroupTouchedAndDirty(this.addActivityForm)
        }
    }

    private refreshActivities() {
        this.showTimeline = false;
        this.activities = [];
        this.activityDataservice.fetchActivity().subscribe((activities: any[]) => {
            activities.forEach(sportActivity => {
                this.showTimeline = true;
                this.activities.push(this.assambleSportActivity(sportActivity));
            })}, error => {
                this.showTimeline = true;
                this.toasterService.pop('error', 'Activity', 'Error during fetching activities');
            }
        );
    }

    /**
     * Marks all controls in a form group as touched
     * @param formGroup - The group to caress..hah
     */
    private markFormGroupTouchedAndDirty(formGroup: FormGroup) {
        (<any>Object).values(formGroup.controls).forEach(control => {
            control.markAsTouched();
            control.markAsDirty();

            if (control.controls) {
                this.markFormGroupTouchedAndDirty(control);
            }
        });
    }

    private refreshAddActivityForm(){
        this.addActivityForm.reset();
        this.addActivityForm.patchValue({
            type: "RUN",
            unit: "kilometers",
            time: "0",
            duration: {
                hours: 0,
                minutes: 0,
                seconds: 0
            }
        })
    }

    private assambleSportActivity(sportActivity:any){
         let sportActivityModel = new SportActivityModel();
         sportActivityModel.id = sportActivity.id;
         sportActivityModel.trackerSource = sportActivity.trackerSource;
         sportActivityModel.sportActivityType = sportActivity.sportActivityType;
         sportActivityModel.title = sportActivity.title;
         sportActivityModel.description = sportActivity.description;
         sportActivityModel.distance = sportActivity.distance;
         sportActivityModel.movingTime = sportActivity.movingTime;
         sportActivityModel.elapsedTime = sportActivity.elapsedTime;
         sportActivityModel.startDate = moment(sportActivity.startDate).format("YYYY-MM-DD HH:mm:ss");
         sportActivityModel.averageSpeed = sportActivity.averageSpeed;
         sportActivityModel.maxSpeed = sportActivity.maxSpeed;
         sportActivityModel.averageTemp = sportActivity.averageTemp;
         sportActivityModel.calories = sportActivity.calories;
         return sportActivityModel;

    }
}
