package br.com.sis.poc.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

/**
 * A AddressDto.
 */
@Entity
@Table(name = "address_dto")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "addressdto")
public class AddressDto implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "address_name")
    private String addressName;

    @Column(name = "address_type")
    private String addressType;

    @Column(name = "city")
    private String city;

    @Column(name = "complement")
    private String complement;

    @Column(name = "country")
    private String country;

    @Column(name = "countryfake")
    private String countryfake;

    @Column(name = "geo_coordinate")
    private String geoCoordinate;

    @Column(name = "neighborhood")
    private String neighborhood;

    @Column(name = "jhi_number")
    private String number;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "receiver_name")
    private String receiverName;

    @Column(name = "reference")
    private String reference;

    @Column(name = "state")
    private String state;

    @Column(name = "street")
    private String street;

    @Column(name = "enterpriseid")
    private String enterpriseid;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddressName() {
        return addressName;
    }

    public AddressDto addressName(String addressName) {
        this.addressName = addressName;
        return this;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getAddressType() {
        return addressType;
    }

    public AddressDto addressType(String addressType) {
        this.addressType = addressType;
        return this;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getCity() {
        return city;
    }

    public AddressDto city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getComplement() {
        return complement;
    }

    public AddressDto complement(String complement) {
        this.complement = complement;
        return this;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getCountry() {
        return country;
    }

    public AddressDto country(String country) {
        this.country = country;
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryfake() {
        return countryfake;
    }

    public AddressDto countryfake(String countryfake) {
        this.countryfake = countryfake;
        return this;
    }

    public void setCountryfake(String countryfake) {
        this.countryfake = countryfake;
    }

    public String getGeoCoordinate() {
        return geoCoordinate;
    }

    public AddressDto geoCoordinate(String geoCoordinate) {
        this.geoCoordinate = geoCoordinate;
        return this;
    }

    public void setGeoCoordinate(String geoCoordinate) {
        this.geoCoordinate = geoCoordinate;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public AddressDto neighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
        return this;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getNumber() {
        return number;
    }

    public AddressDto number(String number) {
        this.number = number;
        return this;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public AddressDto postalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public AddressDto receiverName(String receiverName) {
        this.receiverName = receiverName;
        return this;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReference() {
        return reference;
    }

    public AddressDto reference(String reference) {
        this.reference = reference;
        return this;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getState() {
        return state;
    }

    public AddressDto state(String state) {
        this.state = state;
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreet() {
        return street;
    }

    public AddressDto street(String street) {
        this.street = street;
        return this;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getEnterpriseid() {
        return enterpriseid;
    }

    public AddressDto enterpriseid(String enterpriseid) {
        this.enterpriseid = enterpriseid;
        return this;
    }

    public void setEnterpriseid(String enterpriseid) {
        this.enterpriseid = enterpriseid;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AddressDto addressDto = (AddressDto) o;
        if (addressDto.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), addressDto.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AddressDto{" +
            "id=" + getId() +
            ", addressName='" + getAddressName() + "'" +
            ", addressType='" + getAddressType() + "'" +
            ", city='" + getCity() + "'" +
            ", complement='" + getComplement() + "'" +
            ", country='" + getCountry() + "'" +
            ", countryfake='" + getCountryfake() + "'" +
            ", geoCoordinate='" + getGeoCoordinate() + "'" +
            ", neighborhood='" + getNeighborhood() + "'" +
            ", number='" + getNumber() + "'" +
            ", postalCode='" + getPostalCode() + "'" +
            ", receiverName='" + getReceiverName() + "'" +
            ", reference='" + getReference() + "'" +
            ", state='" + getState() + "'" +
            ", street='" + getStreet() + "'" +
            ", enterpriseid='" + getEnterpriseid() + "'" +
            "}";
    }
}
