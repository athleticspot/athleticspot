import {Routes} from "@angular/router";
import {UserRouteAccessService} from "../shared";
import {userDialogRoute} from "../admin/user-management/user-management.route";
import {activitiesRoute} from "./activities/activities.route";
import {stravaRoute} from "./strava/strava.route";

const TIMELINE_ROUTES = [
    activitiesRoute,
    stravaRoute
];

export const timelineState: Routes = [{
    path: '',
    data: {
        authorities: ['ROLE_USER']
    },
    canActivate: [UserRouteAccessService],
    children: TIMELINE_ROUTES
},
    ...userDialogRoute
];
