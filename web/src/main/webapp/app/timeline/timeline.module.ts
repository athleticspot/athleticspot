import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from "@angular/core";
import {AthleticspotSharedModule} from "../shared/shared.module";
import {RouterModule} from "@angular/router";
import {NgDatepickerModule} from 'ng2-datepicker';
import {ReactiveFormsModule} from "@angular/forms";
import {Ng2DropdownModule} from 'ng2-material-dropdown';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {ActivitiesComponent} from "./activities/activities.component";
import {timelineState} from "./timeline.route";
import {ActivitiesDataservice} from "./activities/activities.dataservice";
import {StravaDataservice} from "./activities/strava.dataservice";
import {InfiniteScrollModule} from "ngx-infinite-scroll";
import {CommonModule} from "@angular/common";

@NgModule({
    imports: [
        CommonModule,
        ReactiveFormsModule,
        AthleticspotSharedModule,
        NgDatepickerModule,
        BrowserAnimationsModule,
        Ng2DropdownModule,
        InfiniteScrollModule,
        RouterModule.forRoot(timelineState, {useHash: true}),
    ],
    declarations: [
        ActivitiesComponent,
    ],
    entryComponents: [ActivitiesComponent],
    providers: [
        ActivitiesDataservice,
        StravaDataservice
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TimelineModule {
}
