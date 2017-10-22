import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from "@angular/core";
import {ResultsComponent} from "./results/results.component";
import {AthleticspotSharedModule} from "../shared/shared.module";
import {RouterModule} from "@angular/router";
import {trainingState} from "./training.route";
import {SurveyComponent} from "./survey/survey.component";
import {NgDatepickerModule} from 'ng2-datepicker';
import {AddResultModalService} from "./results/add-result-modal.service";
import {AddResultComponent} from "./results/add-result.component";
import {TimeDurationPickerModule} from "angular2-time-duration-picker/dist";

@NgModule({
    imports: [
        AthleticspotSharedModule,
        NgDatepickerModule,
        TimeDurationPickerModule,
        RouterModule.forRoot(trainingState, {useHash: true}),
    ],
    declarations: [
        ResultsComponent,
        AddResultComponent,
        SurveyComponent,
    ],
    entryComponents: [AddResultComponent],
    providers: [AddResultModalService],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TrainingModule {
}
