/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ErpTestModule } from '../../../test.module';
import { AddressDtoDetailComponent } from 'app/entities/address-dto/address-dto-detail.component';
import { AddressDto } from 'app/shared/model/address-dto.model';

describe('Component Tests', () => {
    describe('AddressDto Management Detail Component', () => {
        let comp: AddressDtoDetailComponent;
        let fixture: ComponentFixture<AddressDtoDetailComponent>;
        const route = ({ data: of({ addressDto: new AddressDto(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ErpTestModule],
                declarations: [AddressDtoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AddressDtoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AddressDtoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.addressDto).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
