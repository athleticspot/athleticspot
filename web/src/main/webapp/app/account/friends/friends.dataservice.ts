import {Injectable} from "@angular/core";
import {Http, Headers, RequestOptions, Response} from "@angular/http";
import {FollowModel} from "./follow.model";
import 'rxjs/Rx';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class FriendsDataservice {


    constructor(private http: Http) {
    }

    private options = new RequestOptions({ headers: new Headers({ 'Content-Type': 'application/json' }) });


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

    public follow(follow : FollowModel) : Observable<any>{
        return this.http.put('/api/athletes/follow', JSON.stringify(follow));
    }




}
