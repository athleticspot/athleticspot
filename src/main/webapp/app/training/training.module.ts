import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from "@angular/core";
import {TrainingSurveyModule} from "./survey/survey.module";

@NgModule({
    imports: [
        TrainingSurveyModule,
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TrainingModule {}
