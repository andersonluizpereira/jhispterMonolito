/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ErpTestModule } from '../../../test.module';
import { AddressDtoDeleteDialogComponent } from 'app/entities/address-dto/address-dto-delete-dialog.component';
import { AddressDtoService } from 'app/entities/address-dto/address-dto.service';

describe('Component Tests', () => {
    describe('AddressDto Management Delete Component', () => {
        let comp: AddressDtoDeleteDialogComponent;
        let fixture: ComponentFixture<AddressDtoDeleteDialogComponent>;
        let service: AddressDtoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ErpTestModule],
                declarations: [AddressDtoDeleteDialogComponent]
            })
                .overrideTemplate(AddressDtoDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AddressDtoDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AddressDtoService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
