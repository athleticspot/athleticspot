import {Component, OnInit} from "@angular/core";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import * as moment from 'moment';
import {ToasterService} from "angular2-toaster";
import {TrackerDataservice} from "./tracker-dataservice.service";
import {ActivitiesDataservice} from "../../shared/activites/activities.dataservice";
import {SportActivityModel} from "../../shared/activites/sport-activity.model";
import {TrackerSource} from "../../shared/activites/tracker-source";
import {MapsAPILoader} from "@agm/core";
import {NgbTimeStringAdapter} from "./timeticker-string-adapter.service";
import {TrackerInfo} from "./model/tracker-info.model";
import {TranslateService} from "@ngx-translate/core";


@Component({
    selector: 'athleticspot-activities',
    templateUrl: './activities.component.html',
    styleUrls: ['./activities.component.css']
})
export class ActivitiesComponent implements OnInit {

    private activities = [];
    private addActivityForm: FormGroup;
    private showTimeline = false;
    private pageLoading = false;
    private stravaActivationUrl: String;
    private pageCount: 0;
    private currentPage = 0;
    private trackerSource: TrackerSource;
    private trackerInfo = new TrackerInfo();
    private isMetricSystem = true;

    public maxSpeed: number;
    public zoom: number;

    constructor(private activityDataservice: ActivitiesDataservice,
                private stravaDataservice: TrackerDataservice,
                private toasterService: ToasterService,
                private mapsAPILoader: MapsAPILoader,
                private timePickerService: NgbTimeStringAdapter,
                private translateService: TranslateService) {
    }

    ngOnInit(): void {

        console.log(this.translateService.instant("timeline"));

        //set google maps defaults
        this.zoom = 14;
        //load Places Autocomplete
        this.mapsAPILoader.load().then(() => {
        });

        this.trackerSource = TrackerSource.MANUAL;
        this.addActivityForm = new FormGroup({
            'title': new FormControl(null, [Validators.required]),
            'description': new FormControl(),
            'sportActivityType': new FormControl("RUN", [Validators.required]),
            'duration': new FormControl({hour: 12, minute: 0, second: 0}, [Validators.required]),
            'distance': new FormControl(null, [Validators.required]),
            'units': new FormControl("km"),
            'startDate': new FormControl(new Date()),
            'time': new FormControl({hour: 12, minute: 0, second: 0}, [Validators.required]),
            'maxSpeed': new FormControl(),
            'averageSpeed': new FormControl()
        });

        this.addActivityForm.get("units").valueChanges.subscribe(val => {
            if(val === "km"){
                this.isMetricSystem = true;
            }else {
                this.isMetricSystem = false;
            }
        });
        this.refreshActivities();
        this.stravaDataservice.fetchStravaActivationLink()
            .subscribe(value => {
                this.stravaActivationUrl = value;
            });
        this.stravaDataservice.fetchTrackersInfo()
            .subscribe(value => {
                this.trackerInfo = value as TrackerInfo;
                console.log("Recived value: " + value);
                console.log("Tracker info: " + this.trackerInfo);
            });
    }

    submitActivity() {
        if (this.addActivityForm.valid) {
            let activity = this.addActivityForm.value as SportActivityModel;
            activity.trackerSource = TrackerSource.MANUAL;
            activity.startDate = moment(this.addActivityForm.get('startDate').value)
                .startOf('day')
                .add(this.timePickerService.toMinuets(this.addActivityForm.get('time').value),
                    'minutes')
                .format("YYYY-MM-DDTHH:mm:ss");

            activity.duration = this.timePickerService.toSeconds(this.addActivityForm.get('duration').value) + "";
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
            this.markFormGroupTouchedAndDirty(this.addActivityForm);
        }
    }

    private refreshActivities() {
        this.showTimeline = false;
        this.currentPage = 0;
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
            sportActivityType: "RUN",
            startDate: new Date(),
            units: "km",
            time: {hour: 0, minute: 0, second: 0},
            duration: {hour: 0, minute: 0, second: 0}
        })
    }

    private assambleSportActivity(sportActivity: any) {
        let sportActivityModel = new SportActivityModel();
        sportActivityModel.id = sportActivity.id;
        sportActivityModel.firstAndLastName = sportActivity.firstAndLastName;
        sportActivityModel.trackerSource = sportActivity.trackerSource;
        sportActivityModel.sportActivityType =  SportActivityModel
            .resolveSportActivityTypeDescription(
                sportActivity.sportActivityType,
                this.translateService.instant("timeline"));
        sportActivityModel.title = sportActivity.title;
        sportActivityModel.description = sportActivity.description;
        sportActivityModel.distance = sportActivity.distance;
        sportActivityModel.units = sportActivity.units;
        sportActivityModel.movingTime = sportActivity.movingTime;
        sportActivityModel.elapsedTime = sportActivity.elapsedTime;
        sportActivityModel.startDate = moment(sportActivity.startDate).format("LLL");
        sportActivityModel.averageSpeed = sportActivity.averageSpeed;
        sportActivityModel.maxSpeed = sportActivity.maxSpeed;
        sportActivityModel.averageTemp = sportActivity.averageTemp;
        sportActivityModel.calories = sportActivity.calories;
        sportActivityModel.coordinates = sportActivity.coordinates;
        sportActivityModel.formattedPace = sportActivity.formattedPace;
        return sportActivityModel;
    }

    onScroll() {
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

    isMetric(){
        return this.isMetricSystem;
    }
}
