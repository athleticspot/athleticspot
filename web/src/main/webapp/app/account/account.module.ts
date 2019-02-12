import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from "@angular/core";
import {RouterModule} from "@angular/router";

import {AthleticspotSharedModule} from "../shared";

import {
    accountState,
    ActivateComponent,
    ActivateService,
    PasswordComponent,
    PasswordResetFinishComponent,
    PasswordResetFinishService,
    PasswordResetInitComponent,
    PasswordResetInitService,
    PasswordService,
    PasswordStrengthBarComponent,
    Register,
    RegisterComponent,
    SettingsComponent,
    SocialAuthComponent,
    SocialRegisterComponent
} from "./";
import {FriendsComponent} from "./friends/friends.component";
import {FriendsDataservice} from "./friends/friends.dataservice";
import {ReactiveFormsModule} from "@angular/forms";

@NgModule({
    imports: [
        AthleticspotSharedModule,
        ReactiveFormsModule,
        RouterModule.forRoot(accountState, {useHash: true})
    ],
    declarations: [
        SocialRegisterComponent,
        SocialAuthComponent,
        ActivateComponent,
        RegisterComponent,
        PasswordComponent,
        PasswordStrengthBarComponent,
        PasswordResetInitComponent,
        PasswordResetFinishComponent,
        SettingsComponent,
        FriendsComponent
    ],
    providers: [
        Register,
        ActivateService,
        PasswordService,
        PasswordResetInitService,
        PasswordResetFinishService,
        FriendsDataservice
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AthleticspotAccountModule {
}
