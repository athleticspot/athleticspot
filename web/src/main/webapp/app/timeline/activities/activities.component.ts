import {Component, OnInit} from "@angular/core";
import {ActivitiesDataservice} from "./activities.dataservice";

@Component({
    selector: 'athleticspot-activities',
    templateUrl: './activities.component.html'
})
export class ActivitiesComponent implements OnInit {

    constructor(private activityDataservice: ActivitiesDataservice) {
    }

    ngOnInit(): void {
        this.activityDataservice.fetchActivity().subscribe(value =>
            console.log(value)
        );
        console.log("Athleticspot activities list")
    }

}
