export class ActivityDetailsModel {

    private _activityTypes = new Map();

    constructor(){

        this._activityTypes.set("RUN","Running");
        this._activityTypes.set("RIDE","Riding");
        this._activityTypes.set("SWIM","Swimming");
        this._activityTypes.set("HIKE","Hike");
        this._activityTypes.set("WALK","Walk");
        this._activityTypes.set("ALPINE_SKI","Alpine ski");
        this._activityTypes.set("BACKCOUNTRY_SKI","Backcountry ski");
        this._activityTypes.set("CANOEING","Canoeing");

        //     CROSSFIT">Crossfit</option>
        //     EBIKE_RIDE">Ebike ride</option>
        // ELLIPTICAL">Elliptical</option>
        //     ICE_SKATE">Ice skate</option>
        // INLINE_SKATE">Inline skate</option>
        // KAYAKING">Kayaking</option>
        //     KITESURF">Kitesurf</option>
        //     NORDIC_SKI">Nordic ski</option>
        // ROCK_CLIMBING">Rock climbing</option>
        // ROLLERSKI">Rollerski</option>
        //     ROWING">Rowing</option>
        //     SNOWBOARD">Snowboard</option>
        //     STAIR_STEPPER">Stair stepper</option>
        // STAND_UP_PADDLING">Stand up paddling</option>
        // SURFING">Surfing</option>
        //     VIRTUAL_RIDE">Virtual ride</option>
        // WEIGHT_TRAINING">Weight training</option>
        // WINDSURF">Windsurf</option>
        //     WORKOUT">Workout</option>
        //     YOGA">Yoga</option>
        //     UNKNOWN">Unknown</option>
    }


    get activityTypes(): Map<any, any> {
        return this._activityTypes;
    }
}

