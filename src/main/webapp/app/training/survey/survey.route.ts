import {Routes} from "@angular/router";

import {SurveyComponent} from "./survey.component";

export const surveyRoute: Routes = [
    {
    path: 'survey',
    component: SurveyComponent,
    data: {
        pageTitle: 'survey.title'
    }
}];
