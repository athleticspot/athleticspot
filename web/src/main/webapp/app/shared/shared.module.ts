import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from "@angular/core";
import {DatePipe} from "@angular/common";

import {
    AccountService,
    AthleticspotSharedCommonModule,
    AthleticspotSharedLibsModule,
    AuthServerProvider,
    CSRFService,
    HasAnyAuthorityDirective,
    JhiLoginModalComponent,
    JhiSocialComponent,
    LoginModalService,
    LoginService,
    Principal,
    SocialService,
    StateStorageService,
    UserService
} from "./";

@NgModule({
    imports: [
        AthleticspotSharedLibsModule,
        AthleticspotSharedCommonModule
    ],
    declarations: [
        JhiSocialComponent,
        JhiLoginModalComponent,
        HasAnyAuthorityDirective
    ],
    providers: [
        LoginService,
        LoginModalService,
        AccountService,
        StateStorageService,
        Principal,
        CSRFService,
        AuthServerProvider,
        SocialService,
        UserService,
        DatePipe
    ],
    entryComponents: [JhiLoginModalComponent],
    exports: [
        AthleticspotSharedCommonModule,
        JhiSocialComponent,
        JhiLoginModalComponent,
        HasAnyAuthorityDirective,
        DatePipe
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]

})
export class AthleticspotSharedModule {
}
