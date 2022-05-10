package com.ics.openid.connect.models;

import java.io.Serializable;

public interface Address extends Serializable {

    Long getId();

    String getFormattedAddress();

    void setFormattedAddress(String formatted);

    String getStreetAddress();

    void setStreetAddress(String streetAddress);

    String getLocality();

    void setLocality(String locality);

    String getRegion();

    void setRegion(String region);

    String getPostalCode();

    void setPostalCode(String postalCode);

    String getCountry();

    void setCountry(String country);
}
