import {Route} from "@angular/router";

import {ActivitiesComponent} from "./activities.component";

export const activitiesRoute: Route = {
    path: 'activities',
    component: ActivitiesComponent,
    data: {
        pageTitle: 'timeline.title'
    }
};
