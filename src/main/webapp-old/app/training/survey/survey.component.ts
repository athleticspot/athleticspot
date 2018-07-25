import {AfterViewChecked, Component, OnInit} from "@angular/core";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {ScrollHelper} from "./ScrollHelper";
import {SurveyDataservice} from "./survey.dataservice";
import {MetricSystem, SurveyModel} from "./survey.model";
import * as moment from 'moment';

@Component({
    selector: 'athleticspot-survey',
    templateUrl: './survey.component.html'
})
export class SurveyComponent implements OnInit, AfterViewChecked {

    surveyForm: FormGroup;
    isMetricSystem: boolean;
    private scrollHelper: ScrollHelper = new ScrollHelper();


    constructor(private surveyDataservice: SurveyDataservice, private formBuilder: FormBuilder) {
        this.isMetricSystem = true;

        this.surveyForm = this.formBuilder.group({
            trainingSurveyUuid: new FormControl(),
            baseInformation: this.formBuilder.group({
                birthDay: [new Date(), [Validators.required]],
                bodyMass: ['', [Validators.required]],
                height: ['', [Validators.required]],
                metricSystemType: [true, Validators.required]

            }),
            healthInformation: this.formBuilder.group({
                healthContraindications: new FormControl(false, [Validators.required]),
                stressTest: new FormControl(false, [Validators.required]),
                bloodTest: new FormControl(true, [Validators.required]),
                hoursOfSleep: new FormControl('', [Validators.required]),

            }),
            nutritionInformation: this.formBuilder.group({
                meatAcceptance: new FormControl(true, [Validators.required]),
                dairyedAcceptance: new FormControl(true, [Validators.required]),
                allergies: new FormControl(false, [Validators.required]),
                foodIntolerance: new FormControl(false, [Validators.required]),

            })
        });

        new FormGroup({});

        this.surveyForm.get("baseInformation").get('metricSystemType').valueChanges.subscribe(val => {
            console.log(val);
            this.isMetricSystem = val;
        })
    }

    ngOnInit(): void {
        this.surveyDataservice
            .fetchSurvey()
            .subscribe(value => {
                    let survey = value.json() as SurveyModel;
                    this.surveyForm.setValue(survey);
                    if (survey.baseInformation.metricSystemType == "imperial") {
                        this.surveyForm.get("baseInformation").get('metricSystemType').setValue(false);
                    } else {
                        this.surveyForm.get("baseInformation").get('metricSystemType').setValue(true);
                    }
                }
            );
    }

    ngAfterViewChecked() {
        this.scrollHelper.doScroll();
    }


    isMetric() {
        return this.isMetricSystem;
    }

    //TODO: change notification to toastr
    public submitSurvey() {
        if (this.surveyForm.valid) {
            const survey = this.provideSurvey();
            survey.baseInformation.birthDay = moment(this.surveyForm.get("baseInformation").get('birthDay').value).format('YYYY-MM-DD');
            if (survey.trainingSurveyUuid) {
                this.surveyDataservice.updateSurvey(survey).subscribe(isSuccess => {
                    console.log(isSuccess);
                }, error => {
                    console.log(error);
                });
            } else {
                this.surveyDataservice.saveSurvey(survey).subscribe(isSuccess => {
                    console.log(isSuccess);
                }, error => {
                    console.log(error);
                });
            }
        } else {
            this.surveyForm.markAsDirty({onlySelf: true})
            Object.keys(this.surveyForm.controls).forEach(group => {
                const formGroup = this.surveyForm.get(group) as FormGroup;
                Object.keys(formGroup.controls).forEach(control => {
                    formGroup.get(control).markAsDirty({onlySelf: true});
                });
            });
            this.scrollHelper.scrollToFirst("form-control ng-invalid");
        }
    }

    private provideSurvey() {
        const survey = this.surveyForm.value as SurveyModel;
        if (this.isMetric()) {
            survey.baseInformation.metricSystemType = MetricSystem[0];
        } else {
            survey.baseInformation.metricSystemType = MetricSystem[1];
        }
        return JSON.parse(JSON.stringify(survey));
    }
}
