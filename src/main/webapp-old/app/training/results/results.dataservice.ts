import {Injectable} from "@angular/core";
import {Result, Unit} from "./result.model";
import {Http} from "@angular/http";

@Injectable()
export class ResultsDataservice {

    constructor(private http: Http) {
    }


    public getResults(): Result[] {

        return [new Result(
            1,
            5,
            2000,
            Unit.m,
            new Date(),
            3000
        ),

            new Result(
                1,
                29,
                2023,
                Unit.m,
                new Date(),
                3000
            ),
            new Result(
                1,
                3,
                234,
                Unit.m,
                new Date(),
                4135
            )

        ]

    }

    public saveResult(result: Result) {
        this.http.post("/api/result", JSON.stringify(result))
            .subscribe(
                () => {
                    console.log("saved")
                },
                err => console.error(err)
            );
    }

}
