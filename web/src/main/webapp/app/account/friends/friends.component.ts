import {Component, OnInit} from "@angular/core";
import {FriendsDataservice} from "./friends.dataservice";
import {FormControl, FormGroup} from "@angular/forms";

@Component({
    templateUrl: './friends.component.html'
})
export class FriendsComponent implements OnInit {

    private searchFriendsForm: FormGroup;

    constructor(private friendsDataservice: FriendsDataservice) {
    }

    ngOnInit() {
        this.searchFriendsForm = new FormGroup({
            "name": new FormControl(null)
        });
    }

    public searchFriends() {
        let name = this.searchFriendsForm.controls.name.value;
        this.friendsDataservice.fetchFriendsPaged(0, name).subscribe(
            (friendsPage: any) => {
                console.log(friendsPage);
            }, error => {

            }
        );
    }

}
