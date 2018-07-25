import {Injectable} from "@angular/core";
import {NgbModal, NgbModalRef} from "@ng-bootstrap/ng-bootstrap";

import {AddResultComponent} from "./add-result.component";

@Injectable()
export class AddResultModalService {
    private isOpen = false;

    constructor(private modalService: NgbModal,) {
    }

    open(): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;
        const modalRef = this.modalService.open(AddResultComponent, {
            container: 'nav'
        });
        modalRef.result.then((result) => {
            this.isOpen = false;
        }, (reason) => {
            this.isOpen = false;
        });
        return modalRef;
    }
}
