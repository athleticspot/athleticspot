import {Component, Input, OnInit} from "@angular/core";
import {CSRFService} from "../auth/csrf.service";
import {SocialService} from "../social/social.service";

@Component({
    selector: 'athl-spinner',
    templateUrl: './spinner.component.html',
    styleUrls: ['./spinner.component.css']

})
export class AthlSpinnerComponent implements OnInit {

    constructor() {
    }

    ngOnInit() {
    }
}
