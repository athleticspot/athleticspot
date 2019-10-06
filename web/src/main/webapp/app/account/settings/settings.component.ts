import {Component, OnInit} from "@angular/core";
import {JhiLanguageService} from "ng-jhipster";

import {AccountService, JhiLanguageHelper, Principal} from "../../shared";

@Component({
    selector: 'jhi-settings',
    templateUrl: './settings.component.html'
})
export class SettingsComponent implements OnInit {
    error: string;
    success: string;
    settingsAccount: any;
    languages: any[];
    metricSystems = [ {type: 'IMPERIAL', text: 'miles and pounds'}, {type: 'METRIC', text: 'kilometers and kilograms'}];

    constructor(private account: AccountService,
                private principal: Principal,
                private languageService: JhiLanguageService,
                private languageHelper: JhiLanguageHelper) {
    }

    ngOnInit() {
        this.principal.identity().then((account) => {
            this.settingsAccount = SettingsComponent.copyAccount(account);
        });
        this.languageHelper.getAll().then((languages) => {
            this.languages = languages;
        });
    }

    save() {
        this.account.update(this.settingsAccount).subscribe(() => {
            this.error = null;
            this.success = 'OK';
            this.principal.identity(true).then((account) => {
                this.settingsAccount = SettingsComponent.copyAccount(account);
            });
            this.languageService.getCurrent().then((current) => {
                if (this.settingsAccount.langKey !== current) {
                    this.languageService.changeLanguage(this.settingsAccount.langKey);
                }
            });
        }, () => {
            this.success = null;
            this.error = 'ERROR';
        });
    }

    private static copyAccount(account) {
        return {
            activated: account.activated,
            email: account.email,
            firstName: account.firstName,
            langKey: account.langKey,
            lastName: account.lastName,
            login: account.login,
            imageUrl: account.imageUrl,
            metricSystemType: account.metricSystemType
        };
    }
}
