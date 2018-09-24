import {Injectable} from "@angular/core";
import {Http, Response} from "@angular/http";
import 'rxjs/Rx';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class StravaDataservice {

    constructor(private http: Http) {
    }

    public fetchStravaActivationLink(){
        return this.http.get('/api/tracker/strava/activate').map(
            (response: Response) => {
                return response.json();
            }
        );
    }


}
