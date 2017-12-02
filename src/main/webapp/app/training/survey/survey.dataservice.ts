import {Injectable} from "@angular/core";
import {Http} from "@angular/http";
import {SurveyModel} from "./survey.model";
import {Observable} from "rxjs/Observable";

@Injectable()
export class SurveyDataservice {

    constructor(private http: Http) {
    }

    public saveSurvey(survey: SurveyModel): Observable<any> {
        console.log("saving survey");
        return this.http.post('api/survey', survey);
    }


}
