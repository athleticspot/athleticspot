import {Route} from "@angular/router";

import {StravaComponent} from "./strava.component";

export const stravaRoute: Route = {
    path: 'strava',
    component: StravaComponent,
    data: {
        pageTitle: 'timeline.title'
    }
};
