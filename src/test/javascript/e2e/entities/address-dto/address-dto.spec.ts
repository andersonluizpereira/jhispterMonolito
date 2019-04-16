/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { AddressDtoComponentsPage, AddressDtoDeleteDialog, AddressDtoUpdatePage } from './address-dto.page-object';

const expect = chai.expect;

describe('AddressDto e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let addressDtoUpdatePage: AddressDtoUpdatePage;
    let addressDtoComponentsPage: AddressDtoComponentsPage;
    let addressDtoDeleteDialog: AddressDtoDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load AddressDtos', async () => {
        await navBarPage.goToEntity('address-dto');
        addressDtoComponentsPage = new AddressDtoComponentsPage();
        await browser.wait(ec.visibilityOf(addressDtoComponentsPage.title), 5000);
        expect(await addressDtoComponentsPage.getTitle()).to.eq('erpApp.addressDto.home.title');
    });

    it('should load create AddressDto page', async () => {
        await addressDtoComponentsPage.clickOnCreateButton();
        addressDtoUpdatePage = new AddressDtoUpdatePage();
        expect(await addressDtoUpdatePage.getPageTitle()).to.eq('erpApp.addressDto.home.createOrEditLabel');
        await addressDtoUpdatePage.cancel();
    });

    it('should create and save AddressDtos', async () => {
        const nbButtonsBeforeCreate = await addressDtoComponentsPage.countDeleteButtons();

        await addressDtoComponentsPage.clickOnCreateButton();
        await promise.all([
            addressDtoUpdatePage.setAddressNameInput('addressName'),
            addressDtoUpdatePage.setAddressTypeInput('addressType'),
            addressDtoUpdatePage.setCityInput('city'),
            addressDtoUpdatePage.setComplementInput('complement'),
            addressDtoUpdatePage.setCountryInput('country'),
            addressDtoUpdatePage.setCountryfakeInput('countryfake'),
            addressDtoUpdatePage.setGeoCoordinateInput('geoCoordinate'),
            addressDtoUpdatePage.setNeighborhoodInput('neighborhood'),
            addressDtoUpdatePage.setNumberInput('number'),
            addressDtoUpdatePage.setPostalCodeInput('postalCode'),
            addressDtoUpdatePage.setReceiverNameInput('receiverName'),
            addressDtoUpdatePage.setReferenceInput('reference'),
            addressDtoUpdatePage.setStateInput('state'),
            addressDtoUpdatePage.setStreetInput('street'),
            addressDtoUpdatePage.setEnterpriseidInput('enterpriseid')
        ]);
        expect(await addressDtoUpdatePage.getAddressNameInput()).to.eq('addressName');
        expect(await addressDtoUpdatePage.getAddressTypeInput()).to.eq('addressType');
        expect(await addressDtoUpdatePage.getCityInput()).to.eq('city');
        expect(await addressDtoUpdatePage.getComplementInput()).to.eq('complement');
        expect(await addressDtoUpdatePage.getCountryInput()).to.eq('country');
        expect(await addressDtoUpdatePage.getCountryfakeInput()).to.eq('countryfake');
        expect(await addressDtoUpdatePage.getGeoCoordinateInput()).to.eq('geoCoordinate');
        expect(await addressDtoUpdatePage.getNeighborhoodInput()).to.eq('neighborhood');
        expect(await addressDtoUpdatePage.getNumberInput()).to.eq('number');
        expect(await addressDtoUpdatePage.getPostalCodeInput()).to.eq('postalCode');
        expect(await addressDtoUpdatePage.getReceiverNameInput()).to.eq('receiverName');
        expect(await addressDtoUpdatePage.getReferenceInput()).to.eq('reference');
        expect(await addressDtoUpdatePage.getStateInput()).to.eq('state');
        expect(await addressDtoUpdatePage.getStreetInput()).to.eq('street');
        expect(await addressDtoUpdatePage.getEnterpriseidInput()).to.eq('enterpriseid');
        await addressDtoUpdatePage.save();
        expect(await addressDtoUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await addressDtoComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last AddressDto', async () => {
        const nbButtonsBeforeDelete = await addressDtoComponentsPage.countDeleteButtons();
        await addressDtoComponentsPage.clickOnLastDeleteButton();

        addressDtoDeleteDialog = new AddressDtoDeleteDialog();
        expect(await addressDtoDeleteDialog.getDialogTitle()).to.eq('erpApp.addressDto.delete.question');
        await addressDtoDeleteDialog.clickOnConfirmButton();

        expect(await addressDtoComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
