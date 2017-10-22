import {Route} from "@angular/router";

import {SurveyComponent} from "./survey.component";

export const surveyRoute: Route = {
    path: 'survey',
    component: SurveyComponent,
    data: {
        pageTitle: 'survey.title'
    }
};
