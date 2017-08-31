import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from "@angular/core";
import {RouterModule} from "@angular/router";

import {SurveyComponent, surveyRoute, SurveyService} from "./";

const ENTITY_STATES = [
    ...surveyRoute
];

@NgModule({
    imports: [
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        SurveyComponent
    ],
    entryComponents: [
        SurveyComponent
    ],
    providers: [
        SurveyService
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TrainingSurveyModule {}
