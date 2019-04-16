import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAddressDto } from 'app/shared/model/address-dto.model';

@Component({
    selector: 'jhi-address-dto-detail',
    templateUrl: './address-dto-detail.component.html'
})
export class AddressDtoDetailComponent implements OnInit {
    addressDto: IAddressDto;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ addressDto }) => {
            this.addressDto = addressDto;
        });
    }

    previousState() {
        window.history.back();
    }
}
