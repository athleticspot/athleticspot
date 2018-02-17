import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from "@angular/core";
import {ResultsComponent} from "./results/results.component";
import {AthleticspotSharedModule} from "../shared/shared.module";
import {RouterModule} from "@angular/router";
import {trainingState} from "./training.route";
import {SurveyComponent} from "./survey/survey.component";
import {NgDatepickerModule} from 'ng2-datepicker';
import {AddResultModalService} from "./results/add-result-modal.service";
import {AddResultComponent} from "./results/add-result.component";
import {ResultsDataservice} from "./results/results.dataservice";
import {ReactiveFormsModule} from "@angular/forms";
import {Ng2DropdownModule} from 'ng2-material-dropdown';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {SurveyDataservice} from "./survey/survey.dataservice";

@NgModule({
    imports: [
        ReactiveFormsModule,
        AthleticspotSharedModule,
        NgDatepickerModule,
        BrowserAnimationsModule,
        Ng2DropdownModule,
        RouterModule.forRoot(trainingState, {useHash: true}),
    ],
    declarations: [
        ResultsComponent,
        AddResultComponent,
        SurveyComponent,
    ],
    entryComponents: [AddResultComponent],
    providers: [
        AddResultModalService,
        ResultsDataservice,
        SurveyDataservice],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TrainingModule {
}
