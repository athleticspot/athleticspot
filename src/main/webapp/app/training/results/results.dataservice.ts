import {Injectable} from "@angular/core";
import {Result, Unit} from "./result.model";

@Injectable()
export class ResultsDataservice {

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

}
