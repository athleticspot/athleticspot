import {Component} from "@angular/core";
import {Result, Unit} from "./result.model";
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
    durationGroup: FormGroup;
    lastRunTimeGroup: FormGroup;

    public newResult = new Result(
        12,
        4444,
        Unit.m,
        2323,
        new Date(),
        4444
    );

    constructor(resultsDataService: ResultsDataservice) {
        this.resultsDataService = resultsDataService;
        this.date = new Date();
        this.newResultForm = new FormGroup({
            distance: new FormControl('', [Validators.required]),
            lastRunTime: new FormControl(new Date())
        });

        this.durationGroup = new FormGroup({
            hours: new FormControl('', []),
            minutes: new FormControl('', []),
            seconds: new FormControl('', []),
        }, validateDuration);

        this.lastRunTimeGroup = new FormGroup({
                hours: new FormControl('', []),
                minutes: new FormControl('', []),
                seconds: new FormControl('', []),
            }, validateDuration
        );

        this.durationGroup.valueChanges.subscribe(value => {
            console.log(value);
        });


    }

    public addNewResult() {
        this.resultsDataService.saveResult(new Result())
    }

}
