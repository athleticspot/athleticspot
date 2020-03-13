import {Component, OnInit} from "@angular/core";
import {NgbModalRef} from "@ng-bootstrap/ng-bootstrap";
import {AddResultModalService} from "./add-result-modal.service";
import {ResultsDataservice} from "./results.dataservice";
import {ActivitiesDataservice} from "../../shared/activites/activities.dataservice";
import {SportActivityModel} from "../../shared/activites/sport-activity.model";
import * as moment from "moment";
import {ToasterService} from "angular2-toaster";
import {Router} from "@angular/router";
import {JhiEventManager} from "ng-jhipster";

@Component({
    selector: 'results',
    templateUrl: './results.component.html'
})
export class ResultsComponent implements OnInit {

    addResultsModalRef: NgbModalRef;

    constructor(private addResultsModalService: AddResultModalService,
                private resultsDataService: ResultsDataservice,
                private activitiesDataService: ActivitiesDataservice,
                private toasterService: ToasterService,
                private router: Router,
                private eventManager: JhiEventManager) {
    }
    activities = [];

    ngOnInit(): void {
        this.refreshResults();
        this.registerAddResult()
    }

    private refreshResults() {
        this.activities = [];
        this.activitiesDataService.fetchActivityPagedWithSize(0, 10).subscribe((activitiesPage: any) => {
                activitiesPage.content.forEach(sportActivity => {
                    this.activities.push(this.assambleSportActivity(sportActivity));
                });
            }, error => {
                this.toasterService.pop('error', 'Activity', 'Activity fetch failed');
            }
        );
    }

    registerAddResult() {
        this.eventManager.subscribe('athleticspotApp.resultAdded', (message) => {
            this.refreshResults();
        });
    }

    public addActivityResult(): void {
        this.addResultsModalRef = this.addResultsModalService.open();
        this.addResultsModalRef.result
    }

    public goToTimeline() : void {
        this.router.navigate(['/activities'])
    }

    private assambleSportActivity(sportActivity: any) {
        let sportActivityModel = new SportActivityModel();
        sportActivityModel.id = sportActivity.id;
        sportActivityModel.trackerSource = sportActivity.trackerSource;
        sportActivityModel.sportActivityType = sportActivity.sportActivityType;
        sportActivityModel.title = sportActivity.title;
        sportActivityModel.description = sportActivity.description;
        sportActivityModel.distance = sportActivity.distance;
        sportActivityModel.movingTime = sportActivity.movingTime;
        sportActivityModel.elapsedTime = sportActivity.elapsedTime;
        sportActivityModel.startDate = moment(sportActivity.startDate).format("YYYY-MM-DD HH:mm:ss");
        sportActivityModel.averageSpeed = sportActivity.averageSpeed;
        sportActivityModel.maxSpeed = sportActivity.maxSpeed;
        sportActivityModel.averageTemp = sportActivity.averageTemp;
        sportActivityModel.calories = sportActivity.calories;
        return sportActivityModel;

    }
}
