import {Route} from "@angular/router";

import {ResultsComponent} from "./results.component";

export const resultsRoute: Route = {
    path: 'results',
    component: ResultsComponent,
    data: {
        pageTitle: 'survey.title'
    }
};
