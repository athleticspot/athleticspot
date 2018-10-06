import {Injectable} from "@angular/core";
import {Http, Response} from "@angular/http";
import {ActivityModel} from "./activity.model";
import 'rxjs/Rx';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class ActivitiesDataservice {

    constructor(private http: Http) {
    }

    public createActivity(activity: ActivityModel): Observable<any> {
        return this.http.post('api/sportactivities', activity);
    }

    public fetchActivity(){
        return this.http.get('api/sportactivities').map(
            (response: Response) => {
                return response.json();
            }
        );
    }
}
