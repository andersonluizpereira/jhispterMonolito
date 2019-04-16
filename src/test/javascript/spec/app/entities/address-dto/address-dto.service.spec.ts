/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { AddressDtoService } from 'app/entities/address-dto/address-dto.service';
import { IAddressDto, AddressDto } from 'app/shared/model/address-dto.model';

describe('Service Tests', () => {
    describe('AddressDto Service', () => {
        let injector: TestBed;
        let service: AddressDtoService;
        let httpMock: HttpTestingController;
        let elemDefault: IAddressDto;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(AddressDtoService);
            httpMock = injector.get(HttpTestingController);

            elemDefault = new AddressDto(
                0,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA'
            );
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign({}, elemDefault);
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a AddressDto', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .create(new AddressDto(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a AddressDto', async () => {
                const returnedFromService = Object.assign(
                    {
                        addressName: 'BBBBBB',
                        addressType: 'BBBBBB',
                        city: 'BBBBBB',
                        complement: 'BBBBBB',
                        country: 'BBBBBB',
                        countryfake: 'BBBBBB',
                        geoCoordinate: 'BBBBBB',
                        neighborhood: 'BBBBBB',
                        number: 'BBBBBB',
                        postalCode: 'BBBBBB',
                        receiverName: 'BBBBBB',
                        reference: 'BBBBBB',
                        state: 'BBBBBB',
                        street: 'BBBBBB',
                        enterpriseid: 'BBBBBB'
                    },
                    elemDefault
                );

                const expected = Object.assign({}, returnedFromService);
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of AddressDto', async () => {
                const returnedFromService = Object.assign(
                    {
                        addressName: 'BBBBBB',
                        addressType: 'BBBBBB',
                        city: 'BBBBBB',
                        complement: 'BBBBBB',
                        country: 'BBBBBB',
                        countryfake: 'BBBBBB',
                        geoCoordinate: 'BBBBBB',
                        neighborhood: 'BBBBBB',
                        number: 'BBBBBB',
                        postalCode: 'BBBBBB',
                        receiverName: 'BBBBBB',
                        reference: 'BBBBBB',
                        state: 'BBBBBB',
                        street: 'BBBBBB',
                        enterpriseid: 'BBBBBB'
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .query(expected)
                    .pipe(
                        take(1),
                        map(resp => resp.body)
                    )
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a AddressDto', async () => {
                const rxPromise = service.delete(123).subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
