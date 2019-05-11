import {Component, OnInit} from "@angular/core";
import {NgbModalRef} from "@ng-bootstrap/ng-bootstrap";
import {JhiEventManager} from "ng-jhipster";

import {Account, LoginModalService, LoginService, Principal, StateStorageService} from "../shared";
import {CookieService} from "ngx-cookie";
import {Router} from "@angular/router";

@Component({
    selector: 'jhi-home',
    templateUrl: './home.component.html',
    styleUrls: [
        'home.css'
    ]

})
export class HomeComponent implements OnInit {
    account: Account;
    modalRef: NgbModalRef;

    constructor(private principal: Principal,
                private loginModalService: LoginModalService,
                private loginService: LoginService,
                private stateStorageService: StateStorageService,
                private router: Router,
                private cookieService: CookieService,
                private eventManager: JhiEventManager) {
    }

    ngOnInit() {
        const token = this.cookieService.get('social-authentication');
        if (token) {
            this.loginService.loginWithToken(token, false).then(() => {
                this.cookieService.remove('social-authentication');
                // this.router.navigate(['']);

                this.loginService.checkAfterLoginActions().then(() => {
                    // // previousState was set in the authExpiredInterceptor before being redirected to login modal.
                    // // since login is succesful, go to stored previousState and clear previousState
                    const redirect = this.stateStorageService.getUrl();
                    if (redirect) {
                        this.router.navigate([redirect]);
                    }

                }).catch(() => {
                    this.router.navigate(['survey']);
                });


            }, () => {
                this.router.navigate(['social-register'], {queryParams: {'success': 'false'}});
            });
        }
        this.principal.identity().then((account) => {
            this.account = account;
        });
        this.registerAuthenticationSuccess();
        if (this.isAuthenticated()) {
            this.router.navigate(['activities']);
        }
    }

    registerAuthenticationSuccess() {
        this.eventManager.subscribe('authenticationSuccess', (message) => {
            this.principal.identity().then((account) => {
                this.account = account;
            });
        });
    }

    isAuthenticated() {
        return this.principal.isAuthenticated();
    }

    login() {
        this.modalRef = this.loginModalService.open();
    }
}
