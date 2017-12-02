import {AfterViewChecked, Component, OnInit} from "@angular/core";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {ScrollHelper} from "./ScrollHelper";
import {SurveyDataservice} from "./survey.dataservice";
import {MetricSystem, SurveyModel} from "./survey.model";

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
            baseInformation: this.formBuilder.group({
                birthDay: [new Date(), [Validators.required]],
                bodyMass: ['', [Validators.required]],
                height: ['', [Validators.required]],
                metricSystem: [true, Validators.required]

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

        this.surveyForm.get("baseInformation").get('metricSystem').valueChanges.subscribe(val => {
            console.log(val);
            this.isMetricSystem = val;
        })
    }

    ngOnInit(): void {
    }

    ngAfterViewChecked() {
        this.scrollHelper.doScroll();
    }


    isMetric() {
        return this.isMetricSystem;
    }

    public submitSurvey() {
        if (this.surveyForm.valid) {
            console.log('submit');
            const survey = this.surveyForm.value as SurveyModel;



            ///////////

            if(this.isMetric()){
                survey.baseInformation.metricSystemType = MetricSystem[0];
            }else {
                survey.baseInformation.metricSystemType = MetricSystem[1];
            }

            //////////

            console.log(survey);
            this.surveyDataservice.saveSurvey(survey).subscribe(isSuccess => {
                console.log(isSuccess);
            }, error => {
                console.log(error);
            });
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
}
