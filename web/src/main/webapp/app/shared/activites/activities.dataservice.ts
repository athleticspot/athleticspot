import {Injectable} from "@angular/core";
import {Http, Response} from "@angular/http";
import {SportActivityModel} from "./sport-activity.model";
import 'rxjs/Rx';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class ActivitiesDataservice {

    constructor(private http: Http) {
    }

    public createActivity(activity: SportActivityModel): Observable<any> {
        return this.http.post('api/sportactivities', activity);
    }

    public fetchActivity(){
        return this.http.get('api/sportactivities').map(
            (response: Response) => {
                return response.json();
            }
        );
    }

    public fetchActivityPaged(page){
        return this.http.get('/api/sportactivities/paged', {
            params: {
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
