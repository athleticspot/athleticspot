import {AfterViewChecked, Component, OnInit} from "@angular/core";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ScrollHelper} from "./ScrollHelper";

@Component({
    selector: 'athleticspot-survey',
    templateUrl: './survey.component.html'
})
export class SurveyComponent implements OnInit, AfterViewChecked  {

    surveyForm: FormGroup;
    isMetricSystem: boolean;
    private scrollHelper : ScrollHelper = new ScrollHelper();


    constructor() {
        this.isMetricSystem = true;
        this.surveyForm = new FormGroup({
            birthDay: new FormControl(new Date(), [Validators.required]),
            bodyMass: new FormControl('', [Validators.required]),
            height: new FormControl('', [Validators.required]),
            hoursOfSleep: new FormControl('', [Validators.required]),
            metricSystem: new FormControl(true, Validators.required)
        });

        this.surveyForm.get('metricSystem').valueChanges.subscribe(val => {
            console.log(val);
            this.isMetricSystem = val;
        })
    }

    ngOnInit(): void {
    }

    ngAfterViewChecked(){
        this.scrollHelper.doScroll();
    }


    isMetric() {
        return this.isMetricSystem;
    }

    public submitSurvey() {
        if (this.surveyForm.valid) {
            console.log('submit');
        } else {
            this.surveyForm.markAsDirty({onlySelf: true})
            Object.keys(this.surveyForm.controls).forEach(field => {
                const control = this.surveyForm.get(field);
                control.markAsDirty({onlySelf: true});

            });
            this.scrollHelper.scrollToFirst("form-control ng-invalid");
        }
    }
}
