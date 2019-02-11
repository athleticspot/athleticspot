import {Route} from "@angular/router";

import {UserRouteAccessService} from "../../shared";
import {FriendsComponent} from "./friends.component";

export const friendsRoute: Route = {
    path: 'friends',
    component: FriendsComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'friends.title'
    },
    canActivate: [UserRouteAccessService]
};
