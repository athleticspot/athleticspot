export enum Unit{
    km,
    m,
    mil,
}

export class Result{

    constructor(
        public id = 0,
        public distance = 666,
        public _distanceUnit = Unit.m,
        public lifeRecordTime = 500,
        public lastRunDate = new Date(),
        public lastRunTime = 888) {
    }


    public get distanceUnit(): string {
        return Unit[this._distanceUnit];
    }

    public set distanceUnit(value: string) {
        this._distanceUnit = Unit[value];
    }
}
