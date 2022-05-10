package com.ics.oauth2;


import com.ics.common.FieldContainer;

import java.util.HashMap;
import java.util.Map;

public enum GrantType implements FieldContainer {

    AUTHORIZATION_CODE("authorization_code"),
    PASSWORD("password"),
    IMPLICIT("implicit"),
    CLIENT_CREDENTIAL("client_credential"),
    REFRESH_TOKEN("refresh_token");

    private static final Map<String,GrantType> lookup = new HashMap<>();

    static {

        for (GrantType g: GrantType.values()){
            lookup.put(g.getValue(),g);
        }

    }

    private final String value;

    GrantType(String value){
        this.value = value;
    }

    @Override
    public String getValue(){
        return value;
    }

    @Override
    public int getOrdinal() {
        return ordinal();
    }

    public static GrantType getByValue(String value){
        return lookup.get(value);
    }

}
