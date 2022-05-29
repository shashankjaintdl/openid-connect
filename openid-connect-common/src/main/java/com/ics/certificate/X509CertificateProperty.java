package com.ics.certificate;

import com.google.common.base.Strings;

import java.util.HashMap;
import java.util.Map;

public class X509CertificateProperty {


    private  Map<String, String> x509Property = new HashMap<>();


    private String firstName;
    private String lastName;
    private String orgUnit;
    private String orgName;
    private String city;
    private String state;
    private String country;


    public  Map<String, String> getX509Property() {
        return x509Property;
    }

    public void setX509Property(Map<String, String> x509Property) {
        this.x509Property = x509Property;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getOrgUnit() {
        return orgUnit;
    }

    public void setOrgUnit(String orgUnit) {
        this.orgUnit = orgUnit;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName(){
        if (Strings.isNullOrEmpty(getFirstName())){
            throw new IllegalStateException("");
        }
        if (Strings.isNullOrEmpty(getLastName())){
            return getFirstName();
        }
        return getFirstName()+" "+getLastName();
    }

    public String getX509Principal(){

        StringBuilder builder = new StringBuilder();
        builder.append("CN="+getName());
        builder.append(", OU="+getOrgUnit());
        builder.append(", O="+getOrgName());
        builder.append(", L="+getCity());
        builder.append(", ST="+getState());
        builder.append(", C="+getCountry());

        return new String(builder);
    }

}
