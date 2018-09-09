import {Injectable} from "@angular/core";
import {Http, Response} from "@angular/http";
import {ActivityModel} from "./activity.model";
import 'rxjs/Rx';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class ActivitiesDataservice {

    constructor(private http: Http) {
    }

    public createActivity(activity: ActivityModel): Observable<any> {
        return this.http.post('api/sportactivities', activity);
    }

    //
    // public saveSurvey(survey: SurveyModel): Observable<any> {
    //     return this.http.post('api/survey', survey);
    // }
    //
    // public updateSurvey(survey: SurveyModel): Observable<Response>{
    //     return this.http.put('api/survey', survey);
    // }
    //
    public fetchActivity(): Observable<Response>{
        return this.http.get('api/sportactivities').map(
            (response: Response) => {
                return response.json();
            }
        );

    }


}
