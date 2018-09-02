import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from "@angular/core";
import {AthleticspotSharedModule} from "../shared/shared.module";
import {RouterModule} from "@angular/router";
import {NgDatepickerModule} from 'ng2-datepicker';
import {ReactiveFormsModule} from "@angular/forms";
import {Ng2DropdownModule} from 'ng2-material-dropdown';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {ActivitiesComponent} from "./activities/activities.component";
import {timelineState} from "./timeline.route";

@NgModule({
    imports: [
        ReactiveFormsModule,
        AthleticspotSharedModule,
        NgDatepickerModule,
        BrowserAnimationsModule,
        Ng2DropdownModule,
        RouterModule.forRoot(timelineState, {useHash: true}),
    ],
    declarations: [
        ActivitiesComponent,
    ],
    entryComponents: [ActivitiesComponent],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TimelineModule {
}
