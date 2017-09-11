import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from "@angular/core";
import {RouterModule} from "@angular/router";

import {SurveyComponent, surveyRoute, SurveyService} from "./";
import {AchievementListComponent} from "./achivment-results/achievement-list.component";
import {AthleticspotSharedModule} from "../../shared/shared.module";

const ENTITY_STATES = [
    ...surveyRoute
];

@NgModule({
    imports: [
        AthleticspotSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        SurveyComponent,
        AchievementListComponent
    ],
    entryComponents: [
        SurveyComponent,
        AchievementListComponent
    ],
    providers: [
        SurveyService
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TrainingSurveyModule {}
