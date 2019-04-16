import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AddressDto } from 'app/shared/model/address-dto.model';
import { AddressDtoService } from './address-dto.service';
import { AddressDtoComponent } from './address-dto.component';
import { AddressDtoDetailComponent } from './address-dto-detail.component';
import { AddressDtoUpdateComponent } from './address-dto-update.component';
import { AddressDtoDeletePopupComponent } from './address-dto-delete-dialog.component';
import { IAddressDto } from 'app/shared/model/address-dto.model';

@Injectable({ providedIn: 'root' })
export class AddressDtoResolve implements Resolve<IAddressDto> {
    constructor(private service: AddressDtoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IAddressDto> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<AddressDto>) => response.ok),
                map((addressDto: HttpResponse<AddressDto>) => addressDto.body)
            );
        }
        return of(new AddressDto());
    }
}

export const addressDtoRoute: Routes = [
    {
        path: '',
        component: AddressDtoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'erpApp.addressDto.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: AddressDtoDetailComponent,
        resolve: {
            addressDto: AddressDtoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'erpApp.addressDto.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: AddressDtoUpdateComponent,
        resolve: {
            addressDto: AddressDtoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'erpApp.addressDto.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: AddressDtoUpdateComponent,
        resolve: {
            addressDto: AddressDtoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'erpApp.addressDto.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const addressDtoPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: AddressDtoDeletePopupComponent,
        resolve: {
            addressDto: AddressDtoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'erpApp.addressDto.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
