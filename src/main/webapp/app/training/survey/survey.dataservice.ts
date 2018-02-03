import {Injectable} from "@angular/core";
import {Http, Response} from "@angular/http";
import {SurveyModel} from "./survey.model";
import {Observable} from "rxjs/Observable";

@Injectable()
export class SurveyDataservice {

    constructor(private http: Http) {
    }

    public saveSurvey(survey: SurveyModel): Observable<any> {
        return this.http.post('api/survey', survey);
    }

    public fetchSurvey(): Observable<Response>{
        return this.http.get('api/survey');
    }


}
