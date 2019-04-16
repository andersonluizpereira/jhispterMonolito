import { element, by, ElementFinder } from 'protractor';

export class AddressDtoComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-address-dto div table .btn-danger'));
    title = element.all(by.css('jhi-address-dto div h2#page-heading span')).first();

    async clickOnCreateButton() {
        await this.createButton.click();
    }

    async clickOnLastDeleteButton() {
        await this.deleteButtons.last().click();
    }

    async countDeleteButtons() {
        return this.deleteButtons.count();
    }

    async getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class AddressDtoUpdatePage {
    pageTitle = element(by.id('jhi-address-dto-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    addressNameInput = element(by.id('field_addressName'));
    addressTypeInput = element(by.id('field_addressType'));
    cityInput = element(by.id('field_city'));
    complementInput = element(by.id('field_complement'));
    countryInput = element(by.id('field_country'));
    countryfakeInput = element(by.id('field_countryfake'));
    geoCoordinateInput = element(by.id('field_geoCoordinate'));
    neighborhoodInput = element(by.id('field_neighborhood'));
    numberInput = element(by.id('field_number'));
    postalCodeInput = element(by.id('field_postalCode'));
    receiverNameInput = element(by.id('field_receiverName'));
    referenceInput = element(by.id('field_reference'));
    stateInput = element(by.id('field_state'));
    streetInput = element(by.id('field_street'));
    enterpriseidInput = element(by.id('field_enterpriseid'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setAddressNameInput(addressName) {
        await this.addressNameInput.sendKeys(addressName);
    }

    async getAddressNameInput() {
        return this.addressNameInput.getAttribute('value');
    }

    async setAddressTypeInput(addressType) {
        await this.addressTypeInput.sendKeys(addressType);
    }

    async getAddressTypeInput() {
        return this.addressTypeInput.getAttribute('value');
    }

    async setCityInput(city) {
        await this.cityInput.sendKeys(city);
    }

    async getCityInput() {
        return this.cityInput.getAttribute('value');
    }

    async setComplementInput(complement) {
        await this.complementInput.sendKeys(complement);
    }

    async getComplementInput() {
        return this.complementInput.getAttribute('value');
    }

    async setCountryInput(country) {
        await this.countryInput.sendKeys(country);
    }

    async getCountryInput() {
        return this.countryInput.getAttribute('value');
    }

    async setCountryfakeInput(countryfake) {
        await this.countryfakeInput.sendKeys(countryfake);
    }

    async getCountryfakeInput() {
        return this.countryfakeInput.getAttribute('value');
    }

    async setGeoCoordinateInput(geoCoordinate) {
        await this.geoCoordinateInput.sendKeys(geoCoordinate);
    }

    async getGeoCoordinateInput() {
        return this.geoCoordinateInput.getAttribute('value');
    }

    async setNeighborhoodInput(neighborhood) {
        await this.neighborhoodInput.sendKeys(neighborhood);
    }

    async getNeighborhoodInput() {
        return this.neighborhoodInput.getAttribute('value');
    }

    async setNumberInput(number) {
        await this.numberInput.sendKeys(number);
    }

    async getNumberInput() {
        return this.numberInput.getAttribute('value');
    }

    async setPostalCodeInput(postalCode) {
        await this.postalCodeInput.sendKeys(postalCode);
    }

    async getPostalCodeInput() {
        return this.postalCodeInput.getAttribute('value');
    }

    async setReceiverNameInput(receiverName) {
        await this.receiverNameInput.sendKeys(receiverName);
    }

    async getReceiverNameInput() {
        return this.receiverNameInput.getAttribute('value');
    }

    async setReferenceInput(reference) {
        await this.referenceInput.sendKeys(reference);
    }

    async getReferenceInput() {
        return this.referenceInput.getAttribute('value');
    }

    async setStateInput(state) {
        await this.stateInput.sendKeys(state);
    }

    async getStateInput() {
        return this.stateInput.getAttribute('value');
    }

    async setStreetInput(street) {
        await this.streetInput.sendKeys(street);
    }

    async getStreetInput() {
        return this.streetInput.getAttribute('value');
    }

    async setEnterpriseidInput(enterpriseid) {
        await this.enterpriseidInput.sendKeys(enterpriseid);
    }

    async getEnterpriseidInput() {
        return this.enterpriseidInput.getAttribute('value');
    }

    async save() {
        await this.saveButton.click();
    }

    async cancel() {
        await this.cancelButton.click();
    }

    getSaveButton(): ElementFinder {
        return this.saveButton;
    }
}

export class AddressDtoDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-addressDto-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-addressDto'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
