import {Route} from "@angular/router";

import {ResultsComponent} from "./results.component";
import {UserRouteAccessService} from "../../shared";

export const resultsRoute: Route = {
    path: 'results',
    component: ResultsComponent,
    data: {
        pageTitle: 'results.title',
        authorities: ['ROLE_USER']
    },
    canActivate: [UserRouteAccessService],

};
