package com.ics.openid.connect.models;

import com.google.gson.JsonObject;
import com.ics.openid.connect.models.converter.JsonObjectStringConverter;

import javax.persistence.*;

import java.util.Objects;

import static com.ics.openid.connect.models.UserInfoField.*;

@Entity
@Table(name = "user_info")
public class BaseUserInfo implements UserInfo{

    private Long id;

    private String sub;
    private String name;
    private String givenName;
    private String familyName;
    private String middleName;
    private String nickname;
    private String preferredUsername;
    private String profile;
    private String website;
    private String email;
    private boolean emailVerified;
    private String gender;
    private String birthDate;
    private String zoneInfo;
    private String locale;
    private String phoneNo;
    private boolean phoneNoVerified;
    private Address address;
    private String updateAt;
    private JsonObject src;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Basic
    @Override
    @Column(name = "sub")
    public String getSub() {
        return sub;
    }

    @Override
    public void setSub(String sub) {
        this.sub = sub;
    }

    @Basic
    @Override
    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Override
    @Column(name = "given_name")
    public String getGivenName() {
        return givenName;
    }

    @Override
    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    @Basic
    @Override
    @Column(name = "family_name")
    public String getFamilyName() {
        return familyName;
    }

    @Override
    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    @Basic
    @Override
    @Column(name = "middle_name")
    public String getMiddleName() {
        return middleName;
    }

    @Override
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Basic
    @Override
    @Column(name = "nickname")
    public String getNickname() {
        return nickname;
    }

    @Override
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Basic
    @Override
    @Column(name = "preferred_username")
    public String getPreferredUsername() {
        return preferredUsername;
    }

    @Override
    public void setPreferredUsername(String preferredUsername) {
        this.preferredUsername = preferredUsername;
    }

    @Basic
    @Override
    @Column(name = "profile")
    public String getProfile() {
        return profile;
    }

    @Override
    public void setProfile(String profile) {
        this.profile = profile;
    }

    @Basic
    @Override
    @Column(name = "website")
    public String getWebsite() {
        return website;
    }

    @Override
    public void setWebsite(String website) {
        this.website = website;
    }

    @Basic
    @Override
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Override
    @Column(name = "email_verified")
    public boolean isEmailVerified() {
        return emailVerified;
    }

    @Override
    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    @Basic
    @Override
    @Column(name = "gender")
    public String getGender() {
        return gender;
    }

    @Override
    public void setGender(String gender) {
        this.gender = gender;
    }

    @Basic
    @Override
    @Column(name = "birthdate")
    public String getBirthDate() {
        return birthDate;
    }

    @Override
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @Basic
    @Override
    @Column(name = "zone_info")
    public String getZoneInfo() {
        return zoneInfo;
    }

    @Override
    public void setZoneInfo(String zoneInfo) {
        this.zoneInfo = zoneInfo;
    }

    @Basic
    @Override
    @Column(name = "locale")
    public String getLocale() {
        return locale;
    }

    @Override
    public void setLocale(String locale) {
        this.locale = locale;
    }

    @Basic
    @Override
    @Column(name = "phone_number")
    public String getPhoneNo() {
        return phoneNo;
    }

    @Override
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Basic
    @Override
    @Column(name = "phone_number_verified")
    public boolean isPhoneNoVerified() {
        return phoneNoVerified;
    }

    @Override
    public void setPhoneNoVerified(boolean phoneNoVerified) {
        this.phoneNoVerified = phoneNoVerified;
    }

    @Override
    @OneToOne(targetEntity = BaseAddress.class,fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    public Address getAddress() {
        return address;
    }

    @Override
    public void setAddress(Address address) {
        this.address = address;
    }

    @Basic
    @Override
    @Column(name = "update_at")
    public String getUpdateAt() {
        return updateAt;
    }

    @Override
    public void setUpdateAt(String updatedAt) {
        this.updateAt = updatedAt;
    }

    @Override
    public JsonObject toJson() {

        if(src == null){
            JsonObject o = new JsonObject();

            o.addProperty(SUB,getSub());
            o.addProperty(NAME,getName());
            o.addProperty(GIVEN_NAME,getGivenName());
            o.addProperty(FAMILY_NAME,getFamilyName());
            o.addProperty(MIDDLE_NAME,getMiddleName());
            o.addProperty(NICK_NAME,getNickname());
            o.addProperty(PREFERRED_USERNAME,getPreferredUsername());
            o.addProperty(PROFILE,getProfile());
            o.addProperty(WEBSITE,getWebsite());
            o.addProperty(EMAIL,getEmail());
            o.addProperty(EMAIL_VERIFIED,isEmailVerified());
            o.addProperty(GENDER,getGender());
            o.addProperty(BIRTH_DATE,getBirthDate());
            o.addProperty(ZONE_INFO,getZoneInfo());
            o.addProperty(LOCALE,getLocale());
            o.addProperty(PHONE_NO,getPhoneNo());
            o.addProperty(PHONE_NO_VERIFIED,isPhoneNoVerified());

            if(Objects.nonNull(this.getAddress())){
                JsonObject obj = new JsonObject();

                obj.addProperty(FORMATTED, this.getAddress().getFormattedAddress());
                obj.addProperty(STREET_ADDRESS, this.getAddress().getStreetAddress());
                obj.addProperty(LOCALITY, this.getAddress().getLocality());
                obj.addProperty(REGION, this.getAddress().getRegion());
                obj.addProperty(POSTAL_CODE, this.getAddress().getPostalCode());
                obj.addProperty(COUNTRY, this.getAddress().getCountry());

                o.add(ADDRESS,obj);
            }

            o.addProperty(UPDATE_AT, getUpdateAt());

            return o;
        }
        return src;
    }

    @Basic
    @Override
    @Column(name = "src")
    @Convert(converter = JsonObjectStringConverter.class)
    public JsonObject getSource() {
        return src;
    }

    public void setSource(JsonObject src) {
        this.src = src;
    }

}
