import {Injectable} from "@angular/core";
import {Http, Response} from "@angular/http";
import {SportActivityModel} from "../../shared/activites/sport-activity.model";
import {Observable} from "rxjs";

@Injectable()
export class FriendsDataservice {

    constructor(private http: Http) {
    }

    public fetchFriendsPaged(page, name) {
        return this.http.get('/api/athletes/', {
            params: {
                name: name,
                page: page,
                pageSize: "5"
            }
        }).map(
            (response: Response) => {
                return response.json();
            }
        );
    }

    public follow(friendId) {
        return this.http.put('/api/athletes/', {
            body: {
                name: name,
                page: page,
                pageSize: "5"
            }
        }).map(
            (response: Response) => {
                return response.json();
            }
        );
    }




}
