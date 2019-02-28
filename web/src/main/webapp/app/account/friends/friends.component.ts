import {Component, OnInit} from "@angular/core";
import {FriendsDataservice} from "./friends.dataservice";
import {FormControl, FormGroup} from "@angular/forms";
import {FollowModel} from "./follow.model";
import {ToasterService} from "angular2-toaster";
import {TranslateService} from "@ngx-translate/core";

@Component({
    templateUrl: './friends.component.html'
})
export class FriendsComponent implements OnInit {

    private searchFriendsForm: FormGroup;
    private friends = [];
    private showTable = true;
    private pageCount: 0;
    private currentPage = 0;
    private pageLoading = false;

    constructor(private friendsDataservice: FriendsDataservice,
                private toasterService: ToasterService,
                private translateService: TranslateService) {
    }

    ngOnInit() {
        this.searchFriendsForm = new FormGroup({
            "name": new FormControl("")
        });

        console.log(this.translateService.instant("friends"));
    }

    public searchFriends() {
        this.refreshGrid(0);
    }

    public follow(id: Number) {
        this.showTable = false;
        this.friendsDataservice.follow(new FollowModel(id)).subscribe((response: any) => {
                this.toasterService.pop('success', 'Friends', "You fallow new person");
                this.friendsDataservice.fetchFriendsPaged(0, this.searchFriendsForm.controls.name.value)
                    .subscribe((response) => {
                        this.refreshGrid(0)
                    });
            }, error => {
                this.toasterService.pop('error', 'Friends', 'Follow operation error');
            }
        );
    }

    public unfollow(id: Number): void {
        this.friendsDataservice.unfollow(new FollowModel(id)).subscribe((response: any) => {
            this.toasterService.pop('success', 'Friends', 'Unfollow done');
            this.friendsDataservice.fetchFriendsPaged(0, this.searchFriendsForm.controls.name.value)
                .subscribe((response: any) => {
                    this.refreshGrid(0);
                });
        }, error => {
            this.toasterService.pop('success', 'Friends', 'Unfollow operation error');
        });
    }

    private refreshGrid(page: Number) {
        this.currentPage = 0;
        this.pageCount = 0;
        this.pageLoading = false;
        this.showTable = false;
        this.friends = [];
        let name = this.searchFriendsForm.controls.name.value;
        this.friendsDataservice.fetchFriendsPaged(page, name).subscribe(
            (friendsPage: any) => {
                console.log(friendsPage);
                this.pageCount = friendsPage.totalPages;
                friendsPage.content.forEach(friend => {
                    this.friends.push(friend);
                    this.showTable = true;
                });
            }, error => {
                this.showTable = true;
            }
        );
    }

    onScroll() {
        console.log("scrolled!!!!");
        if (this.currentPage < this.pageCount - 1) {
            this.currentPage++;
            console.log("Page count: " + this.pageCount);
            console.log("Current page: " + this.currentPage);
            this.pageLoading = true;
        } else {
            return;
        }
        this.friendsDataservice.fetchFriendsPaged(this.currentPage, name).subscribe(
            (friendsPage: any) => {
                friendsPage.content.forEach(friend => {
                    this.friends.push(friend);
                    this.showTable = true;
                    this.pageLoading = false;
                });
            }, error => {
                this.showTable = true;
                this.pageLoading = false;
            }
        );
    }

}
