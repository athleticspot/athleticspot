import {Injectable} from '@angular/core';
import {NgbTimeStruct} from '@ng-bootstrap/ng-bootstrap';

/**
 * Example of a String Time adapter
 */
@Injectable()
export class NgbTimeStringAdapter {

    fromModel(value: string): NgbTimeStruct {
        if (!value) {
            return null;
        }
        const split = value.split(':');
        return {
            hour: parseInt(split[0], 10),
            minute: parseInt(split[1], 10),
            second: parseInt(split[2], 10)
        };
    }

    toModel(time: NgbTimeStruct): string {
        if (!time) {
            return null;
        }
        return `${this.pad(time.hour)}:${this.pad(time.minute)}:${this.pad(time.second)}`;
    }

    toMinuets(time: NgbTimeStruct): Number {
        if (!time) {
            return 0;
        }
        return time.hour * 60 + time.minute;
    }

    private pad(i: number): string {
        return i < 10 ? `0${i}` : `${i}`;
    }
}
