import {AfterViewChecked, Component, OnInit} from "@angular/core";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {ScrollHelper} from "./ScrollHelper";
import {SurveyDataservice} from "./survey.dataservice";
import {MetricSystem, SurveyModel} from "./survey.model";
import * as moment from 'moment';
import {ToasterService} from "angular2-toaster";
import {Router} from "@angular/router";

@Component({
    selector: 'athleticspot-survey',
    templateUrl: './survey.component.html'
})
export class SurveyComponent implements OnInit, AfterViewChecked {

    surveyForm: FormGroup;
    isMetricSystem: boolean;
    private scrollHelper: ScrollHelper = new ScrollHelper();

    constructor(private router: Router,
                private surveyDataservice: SurveyDataservice,
                private formBuilder: FormBuilder,
                private toasterService: ToasterService) {

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
            this.isMetricSystem = val;
        })
    }

    ngOnInit(): void {
        this.surveyDataservice
            .fetchSurvey()
            .subscribe(value => {
                    let survey = value.json() as SurveyModel;
                    this.surveyForm.setValue(survey);
                    if (survey.baseInformation.metricSystemType == "IMPERIAL") {
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

    public submitSurvey() {
        if (this.surveyForm.valid) {
            const survey = this.provideSurvey();
            survey.baseInformation.birthDay = moment(this.surveyForm.get("baseInformation").get('birthDay').value).format('YYYY-MM-DD');
            if (survey.trainingSurveyUuid) {
                this.surveyDataservice.updateSurvey(survey).subscribe(isSuccess => {
                    this.toasterService.pop('success', 'Training survey', 'Survey updated');
                }, error => {
                    this.toasterService.pop('error', 'Training survey', 'Error saving survey');
                });
            } else {
                this.surveyDataservice.saveSurvey(survey).subscribe(isSuccess => {
                    this.toasterService.pop('success', 'Training survey', 'Survey saved');
                    this.router.navigate(['/results'])
                }, error => {
                    this.toasterService.pop('error', 'Training survey', 'Error saving survey');
                });
            }
        } else {
            this.toasterService.pop('error', 'Training survey', 'Training survey has errors');
            this.surveyForm.markAsDirty({onlySelf: true});
            this.markFormGroupTouchedAndDirty(this.surveyForm);
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
