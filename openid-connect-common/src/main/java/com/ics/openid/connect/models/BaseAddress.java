package com.ics.openid.connect.models;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class BaseAddress implements Address {

    private Long id;

    private String formattedAddress;
    private String streetAddress;
    private String locality;
    private String region;
    private String postalCode;
    private String country;


    public BaseAddress(){}

    public BaseAddress(Address address){
        setFormattedAddress(address.getFormattedAddress());
        setStreetAddress(address.getStreetAddress());
        setLocality(address.getLocality());
        setRegion(address.getRegion());
        setPostalCode(address.getPostalCode());
        setCountry(address.getCountry());
    }

    @Id
    @Override
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    @Basic
    @Column(name = "formatted_address")
    @Override
    public String getFormattedAddress() {
        return formattedAddress;
    }

    @Override
    public void setFormattedAddress(String formatted) {
        this.formattedAddress = formatted;
    }

    @Basic
    @Column(name = "street_address")
    @Override
    public String getStreetAddress() {
        return streetAddress;
    }

    @Override
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    @Basic
    @Override
    @Column(name = "locality")
    public String getLocality() {
        return locality;
    }

    @Override
    public void setLocality(String locality) {
        this.locality = locality;
    }

    @Basic
    @Override
    @Column(name = "region")
    public String getRegion() {
        return region;
    }

    @Override
    public void setRegion(String region) {
        this.region = region;
    }

    @Basic
    @Override
    @Column(name = "postal_code")
    public String getPostalCode() {
        return postalCode;
    }

    @Override
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Basic
    @Override
    @Column(name = "country")
    public String getCountry() {
        return country;
    }

    @Override
    public void setCountry(String country) {
        this.country = country;
    }


}
