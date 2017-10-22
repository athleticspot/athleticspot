import {Component} from "@angular/core";


@Component({
    selector: 'add-result-modal',
    templateUrl: './add-result.component.html'
})
export class AddResultComponent {

    date: Date;

    constructor(){
        this.date = new Date();

    }

}
