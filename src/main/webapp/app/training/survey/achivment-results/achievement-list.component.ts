import {Component, OnInit} from "@angular/core";
import {Achievement} from "../model/achievement.model";
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
    selector: 'achievement-list',
    templateUrl: './achievement-list.component.html'
})
export class AchievementListComponent implements OnInit {

    date: Date;
    form: FormGroup;


    constructor() {
        this.date = new Date();
    }

    achievements: Achievement[] = [];
    ngOnInit(): void {

        let achievement = new Achievement();
        achievement.id = 1;
        achievement.distance = 21;
        achievement.date = '21/02/2017';
        achievement.time = '1h 21s';
        this.achievements.push(achievement);
    }



}
