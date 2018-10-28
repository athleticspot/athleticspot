import {Component, OnInit} from "@angular/core";
import {Unit} from "./result.model";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {validateDuration} from "./validateDuration";
import {ResultsDataservice} from "./results.dataservice";
import {SportActivityModel} from "../../shared/activites/sport-activity.model";
import * as moment from "moment";
import {ActivitiesDataservice} from "../../shared/activites/activities.dataservice";
import {ToasterService} from "angular2-toaster";
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {JhiEventManager} from "ng-jhipster";

@Component({
    selector: 'add-result-modal',
    templateUrl: './add-result.component.html'
})
export class AddResultComponent implements OnInit {

    date: Date;
    unit: Unit;
    resultsDataService: ResultsDataservice;
    newResultForm: FormGroup;

    // durationGroup: FormGroup;

    constructor(private activityDataservice: ActivitiesDataservice,
                private toasterService: ToasterService,
                public activeModal: NgbActiveModal,
                private eventManager: JhiEventManager) {


        // this.durationGroup = new FormGroup({
        //     hours: new FormControl('', []),
        //     minutes: new FormControl('', []),
        //     seconds: new FormControl('', []),
        // }, validateDuration);
        //

        // this.durationGroup.valueChanges.subscribe(value => {
        //     console.log(value);
        // });
    }

    ngOnInit(): void {
        this.newResultForm = new FormGroup({
            'title': new FormControl(null, [Validators.required]),
            'distance': new FormControl(0, [Validators.required]),
            'duration': new FormGroup({
                'hours': new FormControl('0', [Validators.required]),
                'minutes': new FormControl('0', [Validators.required]),
                'seconds': new FormControl('0', [Validators.required]),
            }, validateDuration),
            'startDate': new FormControl(new Date())
        });
    }

    public submitActivity(): void {
        if (this.newResultForm.valid) {
            let activity = this.newResultForm.value as SportActivityModel;
            activity.trackerSource = "MANUAL";
            activity.sportActivityType = "RUN";
            activity.startDate = moment(this.newResultForm.get('startDate').value)
                .startOf('day')
                .add(0,
                    'hours')
                .format("YYYY-MM-DDTHH:mm:ss");

            activity.duration =
                this.newResultForm.get("duration.hours").value * 36000 + +
                    this.newResultForm.get("duration.minutes").value * 60 +
                this.newResultForm.get("duration.seconds").value;
            this.activityDataservice.createActivity(activity).subscribe(isSuccess => {
                this.toasterService.pop('success', 'Activity', 'Activity created successfully');
                this.eventManager.broadcast({name: 'athleticspotApp.resultAdded', content: {}});

            }, error => {
                this.toasterService.pop('error', 'Activity', 'Activity create failed');
            });
            this.activeModal.dismiss();
        } else {
            this.newResultForm.markAsDirty({onlySelf: true});
            this.markFormGroupTouchedAndDirty(this.newResultForm)
        }
    }

    public cancel() :void{
        this.activeModal.dismiss();
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
}
