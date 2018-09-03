import {Injectable} from "@angular/core";
import {Http, Response} from "@angular/http";
import {Observable} from "rxjs/Observable";
import {ActivityModel} from "./activity.model";

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
        return this.http.get('api/sportactivities');

    }


}
