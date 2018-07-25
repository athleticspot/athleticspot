import {Routes} from "@angular/router";
import {UserRouteAccessService} from "../shared";
import {surveyRoute} from "./survey/survey.route";
import {resultsRoute} from "./results/results.route";
import {userDialogRoute} from "../admin/user-management/user-management.route";

const TRAINING_ROUTES = [
    surveyRoute,
    resultsRoute
];

export const trainingState: Routes = [{
    path: '',
    data: {
        // authorities: ['ROLE_ADMIN']
    },
    canActivate: [UserRouteAccessService],
    children: TRAINING_ROUTES
},
    ...userDialogRoute
];
