import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IAddressDto } from 'app/shared/model/address-dto.model';
import { AddressDtoService } from './address-dto.service';

@Component({
    selector: 'jhi-address-dto-update',
    templateUrl: './address-dto-update.component.html'
})
export class AddressDtoUpdateComponent implements OnInit {
    addressDto: IAddressDto;
    isSaving: boolean;

    constructor(protected addressDtoService: AddressDtoService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ addressDto }) => {
            this.addressDto = addressDto;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.addressDto.id !== undefined) {
            this.subscribeToSaveResponse(this.addressDtoService.update(this.addressDto));
        } else {
            this.subscribeToSaveResponse(this.addressDtoService.create(this.addressDto));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IAddressDto>>) {
        result.subscribe((res: HttpResponse<IAddressDto>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
