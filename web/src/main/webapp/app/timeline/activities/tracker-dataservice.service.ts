import {Injectable} from "@angular/core";
import {Http, Response} from "@angular/http";
import 'rxjs/Rx';

@Injectable()
export class TrackerDataservice {

    constructor(private http: Http) {
    }

    public fetchStravaActivationLink(){
        return this.http.get('/api/tracker/strava/activate').map(
            (response: Response) => {
                return response.json();
            }
        );
    }

    public fetchTrackersInfo(){
        return this.http.get('/api/tracker/info').map(
            (response: Response) => {
                return response.json();
            }
        );
    }


}
