export interface IAddressDto {
    id?: number;
    addressName?: string;
    addressType?: string;
    city?: string;
    complement?: string;
    country?: string;
    countryfake?: string;
    geoCoordinate?: string;
    neighborhood?: string;
    number?: string;
    postalCode?: string;
    receiverName?: string;
    reference?: string;
    state?: string;
    street?: string;
    enterpriseid?: string;
}

export class AddressDto implements IAddressDto {
    constructor(
        public id?: number,
        public addressName?: string,
        public addressType?: string,
        public city?: string,
        public complement?: string,
        public country?: string,
        public countryfake?: string,
        public geoCoordinate?: string,
        public neighborhood?: string,
        public number?: string,
        public postalCode?: string,
        public receiverName?: string,
        public reference?: string,
        public state?: string,
        public street?: string,
        public enterpriseid?: string
    ) {}
}
