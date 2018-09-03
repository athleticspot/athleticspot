export class ActivityModel {

    constructor(
        public _source = "MANUAL",
        public _title = "title",
        public _description = "description",
        public _type = "running",
        public _duration = "25",
        public _distance = "10",
        public _units = "km",
        public _maxSpeed = "33",
        public _meanSpeed = "15",
        public _dateTime = new Date()) {
    }


    get source(): string {
        return this._source;
    }

    set source(value: string) {
        this._source = value;
    }

    get title(): string {
        return this._title;
    }

    set title(value: string) {
        this._title = value;
    }

    get description(): string {
        return this._description;
    }

    set description(value: string) {
        this._description = value;
    }

    get type(): string {
        return this._type;
    }

    set type(value: string) {
        this._type = value;
    }

    get duration(): string {
        return this._duration;
    }

    set duration(value: string) {
        this._duration = value;
    }

    get distance(): string {
        return this._distance;
    }

    set distance(value: string) {
        this._distance = value;
    }

    get units(): string {
        return this._units;
    }

    set units(value: string) {
        this._units = value;
    }

    get maxSpeed(): string {
        return this._maxSpeed;
    }

    set maxSpeed(value: string) {
        this._maxSpeed = value;
    }

    get meanSpeed(): string {
        return this._meanSpeed;
    }

    set meanSpeed(value: string) {
        this._meanSpeed = value;
    }

    get dateTime(): Date {
        return this._dateTime;
    }

    set dateTime(value: Date) {
        this._dateTime = value;
    }
}
