package com.ics.oauth2.models;

import java.util.Date;

public class AuthorizationCodeEntity {

    private Long id;

    private String code;

    private Date expiration;

    public AuthorizationCodeEntity(){}

    public AuthorizationCodeEntity(String code, Date expiration){
        this.code = code;
        this.expiration = expiration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }


}
