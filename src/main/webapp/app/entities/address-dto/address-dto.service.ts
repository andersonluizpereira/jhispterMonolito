import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAddressDto } from 'app/shared/model/address-dto.model';

type EntityResponseType = HttpResponse<IAddressDto>;
type EntityArrayResponseType = HttpResponse<IAddressDto[]>;

@Injectable({ providedIn: 'root' })
export class AddressDtoService {
    public resourceUrl = SERVER_API_URL + 'api/address-dtos';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/address-dtos';

    constructor(protected http: HttpClient) {}

    create(addressDto: IAddressDto): Observable<EntityResponseType> {
        return this.http.post<IAddressDto>(this.resourceUrl, addressDto, { observe: 'response' });
    }

    update(addressDto: IAddressDto): Observable<EntityResponseType> {
        return this.http.put<IAddressDto>(this.resourceUrl, addressDto, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IAddressDto>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAddressDto[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAddressDto[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
    }
}
