import {Component, OnInit} from "@angular/core";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import * as moment from 'moment';
import {ToasterService} from "angular2-toaster";
import {StravaDataservice} from "./strava.dataservice";
import {ActivitiesDataservice} from "../../shared/activites/activities.dataservice";
import {SportActivityModel} from "../../shared/activites/sport-activity.model";


@Component({
    selector: 'athleticspot-activities',
    templateUrl: './activities.component.html'
})
export class ActivitiesComponent implements OnInit {

    private activities = [];
    private addActivityForm: FormGroup;
    private showTimeline = false;
    private pageLoading = false;
    private stravaActivationUrl: String;
    private pageCount: 0;
    private currentPage = 0;


    constructor(private activityDataservice: ActivitiesDataservice,
                private stravaDataservice: StravaDataservice,
                private toasterService: ToasterService) {
    }

    ngOnInit(): void {
        this.addActivityForm = new FormGroup({
            'title': new FormControl(null, [Validators.required]),
            'description': new FormControl(),
            'sportActivityType': new FormControl("RUN", [Validators.required]),
            'duration': new FormGroup({
                "hours": new FormControl(0),
                "minutes": new FormControl(0),
                "seconds": new FormControl(0)
            }),
            'distance': new FormControl(),
            'unit': new FormControl("kilometers"),
            'startDate': new FormControl(new Date()),
            'time': new FormControl("0", [Validators.required]),
            'maxSpeed': new FormControl(),
            'averageSpeed': new FormControl()
        });
        this.refreshActivities();
        this.stravaDataservice.fetchStravaActivationLink()
            .subscribe(value => {
                this.stravaActivationUrl = value;
            })
    }

    submitActivity() {
        if (this.addActivityForm.valid) {
            let activity = this.addActivityForm.value as SportActivityModel;
            activity.trackerSource = "MANUAL";
            activity.startDate = moment(this.addActivityForm.get('startDate').value)
                .startOf('day')
                .add(this.addActivityForm.get('time').value,
                    'hours')
                .format("YYYY-MM-DDTHH:mm:ss");

            activity.duration =
                this.addActivityForm.get("duration.hours").value * 36000 + +
                this.addActivityForm.get("duration.minutes").value * 60 +
                this.addActivityForm.get("duration.seconds").value;
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
        this.activityDataservice.fetchActivityPaged(this.currentPage).subscribe((activitiesPage: any) => {
                this.pageCount = activitiesPage.totalPages;
                activitiesPage.content.forEach(sportActivity => {
                    this.activities.push(this.assambleSportActivity(sportActivity));
                });
                this.showTimeline = true;
            }, error => {
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

    private refreshAddActivityForm() {
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

    private assambleSportActivity(sportActivity: any) {
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

    onScroll() {
        console.log("scrolled!!!!");
        if (this.currentPage < this.pageCount - 1) {
            this.currentPage++;
            console.log("Page count: " + this.pageCount);
            console.log("Current page: " + this.currentPage);
            this.pageLoading = true;
        } else {
            return;
        }
        this.activityDataservice.fetchActivityPaged(this.currentPage).subscribe((activitiesPage: any) => {
                activitiesPage.content.forEach(sportActivity => {
                    this.activities.push(this.assambleSportActivity(sportActivity));
                    this.pageLoading = false;
                });
            }, error => {
                this.toasterService.pop('error', 'Activity', 'Error during fetching activities');
                this.pageLoading = false;
            }
        );
    }
}
