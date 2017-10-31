import {Component, OnInit} from "@angular/core";
import {Result} from "./result.model";
import {NgbModalRef} from "@ng-bootstrap/ng-bootstrap";
import {AddResultModalService} from "./add-result-modal.service";
import {ResultsDataservice} from "./results.dataservice";

@Component({
    selector: 'results',
    templateUrl: './results.component.html'
})
export class ResultsComponent implements OnInit {

    addResultsModalRef: NgbModalRef;

    constructor(private addResultsModalService: AddResultModalService,
                private resultsDataService: ResultsDataservice) {
    }

    results: Result[] = [];

    ngOnInit(): void {
        this.results = this.resultsDataService.getResults();
    }

    public click(): void {
        this.addResultsModalRef = this.addResultsModalService.open();
    }
}
