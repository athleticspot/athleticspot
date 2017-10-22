import {Component, OnInit} from "@angular/core";
import {Achievement} from "./achievement.model";
import {NgbModalRef} from "@ng-bootstrap/ng-bootstrap";
import {AddResultModalService} from "./add-result-modal.service";

@Component({
    selector: 'results',
    templateUrl: './results.component.html'
})
export class ResultsComponent implements OnInit {

    date: Date;
    modalRef: NgbModalRef;

    constructor(private addResultsModalService: AddResultModalService,) {
        this.date = new Date();
    }

    achievements: Achievement[] = [];

    ngOnInit(): void {

        let achievement = new Achievement();
        achievement.id = 1;
        achievement.distance = 21;
        achievement.date = '21/02/2017';
        achievement.time = '1h 21s';
        this.achievements.push(achievement);
    }

    public click(): void {
        this.modalRef = this.addResultsModalService.open();
    }


}
