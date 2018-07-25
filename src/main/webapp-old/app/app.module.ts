import "./vendor.ts";

import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {Ng2Webstorage} from "ng2-webstorage";

import {AthleticspotSharedModule, UserRouteAccessService} from "./shared";
import {AthleticspotHomeModule} from "./home/home.module";
import {AthleticspotAdminModule} from "./admin/admin.module";
import {AthleticspotAccountModule} from "./account/account.module";
import {AthleticspotEntityModule} from "./entities/entity.module";

import {customHttpProvider} from "./blocks/interceptor/http.provider";
import {PaginationConfig} from "./blocks/config/uib-pagination.config";
import {
    ActiveMenuDirective,
    ErrorComponent,
    FooterComponent,
    JhiMainComponent,
    LayoutRoutingModule,
    NavbarComponent,
    PageRibbonComponent,
    ProfileService
} from "./layouts";
import {TrainingModule} from "./training/training.module";

// jhipster-needle-angular-add-module-import JHipster will add new module here

@NgModule({
    imports: [
        BrowserModule,
        LayoutRoutingModule,
        Ng2Webstorage.forRoot({prefix: 'jhi', separator: '-'}),
        AthleticspotSharedModule,
        AthleticspotHomeModule,
        AthleticspotAdminModule,
        AthleticspotAccountModule,
        AthleticspotEntityModule,
        TrainingModule
        // jhipster-needle-angular-add-module JHipster will add new module here
    ],
    declarations: [
        JhiMainComponent,
        NavbarComponent,
        ErrorComponent,
        PageRibbonComponent,
        ActiveMenuDirective,
        FooterComponent
    ],
    providers: [
        ProfileService,
        customHttpProvider(),
        PaginationConfig,
        UserRouteAccessService
    ],
    bootstrap: [JhiMainComponent]
})
export class AthleticspotAppModule {
}
