import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { ErpSharedModule } from 'app/shared';
import {
    AddressDtoComponent,
    AddressDtoDetailComponent,
    AddressDtoUpdateComponent,
    AddressDtoDeletePopupComponent,
    AddressDtoDeleteDialogComponent,
    addressDtoRoute,
    addressDtoPopupRoute
} from './';

const ENTITY_STATES = [...addressDtoRoute, ...addressDtoPopupRoute];

@NgModule({
    imports: [ErpSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AddressDtoComponent,
        AddressDtoDetailComponent,
        AddressDtoUpdateComponent,
        AddressDtoDeleteDialogComponent,
        AddressDtoDeletePopupComponent
    ],
    entryComponents: [AddressDtoComponent, AddressDtoUpdateComponent, AddressDtoDeleteDialogComponent, AddressDtoDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ErpAddressDtoModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
