import {Injectable} from "@angular/core";
import {Http} from "@angular/http";
import {Observable} from "rxjs";
import {ActivityModel} from "./activity.model";

@Injectable()
export class ActivitiesDataservice {

    constructor(private http: Http) {
    }

    public createActivity(activity: ActivityModel): Observable<any> {
        return this.http.post('api/activity', activity);
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
    // public fetchSurvey(): Observable<Response>{
    //     return this.http.get('api/survey');
    // }


}
