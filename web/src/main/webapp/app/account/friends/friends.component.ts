import {Component, OnInit} from "@angular/core";
import {FriendsDataservice} from "./friends.dataservice";
import {FormControl, FormGroup} from "@angular/forms";
import {FollowModel} from "./follow.model";
import {Observable} from "rxjs";

@Component({
    templateUrl: './friends.component.html'
})
export class FriendsComponent implements OnInit {

    private searchFriendsForm: FormGroup;
    private friends = [];

    constructor(private friendsDataservice: FriendsDataservice) {
    }

    ngOnInit() {
        this.searchFriendsForm = new FormGroup({
            "name": new FormControl("")
        });
    }

    public searchFriends() {
        this.friends = [];
        let name = this.searchFriendsForm.controls.name.value;
        this.friendsDataservice.fetchFriendsPaged(0, name).subscribe(
            (friendsPage: any) => {
                console.log(friendsPage);
                friendsPage.content.forEach(friend => {
                    this.friends.push(friend)
                });
            }, error => {
                //TODO: add toastr
            }
        );
    }

    public follow(id: Number){
        this.friendsDataservice.follow(new FollowModel(id)).subscribe((response: any) => {
                console.log(response);
            }, error => {
                //TODO: add toastr

            }
        );
    }

}
