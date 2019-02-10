import {Route} from "@angular/router";

import {UserRouteAccessService} from "../../shared";
import {FriendsComponent} from "./friends.component";

export const friendsRoute: Route = {
    path: 'friends',
    component: FriendsComponent,
    data: {
        authorities: [],
        pageTitle: 'friends.title'
    },
    canActivate: [UserRouteAccessService]
};
