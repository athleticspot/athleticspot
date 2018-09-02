import {Component, OnInit} from "@angular/core";

@Component({
    selector: 'athleticspot-activities',
    templateUrl: './activities.component.html'
})
export class ActivitiesComponent implements OnInit{

    ngOnInit(): void {
        console.log("Athleticspot activities list")
    }

}
