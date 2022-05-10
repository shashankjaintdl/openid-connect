package com.ics.openid.connect.models;

import com.google.gson.JsonObject;

import java.io.Serializable;

public interface UserInfo extends Serializable {

    String getSub();

    void setSub(String sub);

    String getName();

    void setName(String name);

    String getGivenName();

    void setGivenName(String givenName);

    String getFamilyName();

    void setFamilyName(String familyName);

    String getMiddleName();

    void setMiddleName(String middleName);

    String getNickname();

    void setNickname(String nickname);

    String getPreferredUsername();

    void setPreferredUsername(String preferredUsername);

    String getProfile();

    void setProfile(String profile);

    String getWebsite();

    void setWebsite(String website);

    String getEmail();

    void setEmail(String email);

    boolean isEmailVerified();

    void setEmailVerified(boolean emailVerified);

    String getGender();

    void setGender(String gender);

    String getBirthDate();

    void setBirthDate(String birthDate);

    String getZoneInfo();

    void setZoneInfo(String zoneInfo);

    String getLocale();

    void setLocale(String locale);

    String getPhoneNo();

    void setPhoneNo(String phoneNo);

    boolean isPhoneNoVerified();

    void setPhoneNoVerified(boolean phoneNoVerified);

    Address getAddress();

    void setAddress(Address address);

    String getUpdateAt();

    void setUpdateAt(String updatedAt);

    JsonObject toJson();

    JsonObject getSource();
}
