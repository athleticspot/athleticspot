import {Component} from "@angular/core";
import {Unit} from "./result.model";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {validateDuration} from "./validateDuration";
import {ResultsDataservice} from "./results.dataservice";

@Component({
    selector: 'add-result-modal',
    templateUrl: './add-result.component.html'
})
export class AddResultComponent {

    date: Date;
    unit: Unit;
    resultsDataService: ResultsDataservice;
    newResultForm: FormGroup;
    // durationGroup: FormGroup;

    constructor(resultsDataService: ResultsDataservice) {
        this.resultsDataService = resultsDataService;
        this.newResultForm = new FormGroup({
            'distance': new FormControl('', [Validators.required]),

            'durationGroup': new FormGroup({
                hours: new FormControl('0', [Validators.required]),
                minutes: new FormControl('0', [Validators.required]),
                seconds: new FormControl('0', [Validators.required]),
            }, validateDuration),
            'activityDate': new FormControl(new Date())
        });

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

    public submitActivity(): void {
        // this.resultsDataService.saveResult(new Result())
        console.log(this.newResultForm);

    }

}
