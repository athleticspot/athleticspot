import {Route} from "@angular/router";

import {SurveyComponent} from "./survey.component";
import {UserRouteAccessService} from "../../shared";

export const surveyRoute: Route = {
    path: 'survey',
    component: SurveyComponent,
    data: {
        pageTitle: 'survey.title',
        authorities: ['ROLE_USER']
    },
    canActivate: [UserRouteAccessService],
};
