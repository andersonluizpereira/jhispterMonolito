/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ErpTestModule } from '../../../test.module';
import { AddressDtoUpdateComponent } from 'app/entities/address-dto/address-dto-update.component';
import { AddressDtoService } from 'app/entities/address-dto/address-dto.service';
import { AddressDto } from 'app/shared/model/address-dto.model';

describe('Component Tests', () => {
    describe('AddressDto Management Update Component', () => {
        let comp: AddressDtoUpdateComponent;
        let fixture: ComponentFixture<AddressDtoUpdateComponent>;
        let service: AddressDtoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ErpTestModule],
                declarations: [AddressDtoUpdateComponent]
            })
                .overrideTemplate(AddressDtoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AddressDtoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AddressDtoService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new AddressDto(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.addressDto = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new AddressDto();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.addressDto = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
