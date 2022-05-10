package com.ics.oauth2;


import com.ics.common.FieldContainer;

import java.util.HashMap;
import java.util.Map;

public enum TokenEndPointAuthMethod implements FieldContainer {

    /**
     *
     *  Requested Client Authentication method for the Token Endpoint.
     *  The options are client_secret_post, client_secret_basic, client_secret_jwt, private_key_jwt, and none.
     *  Other authentication methods MAY be defined by extensions.
     *  If omitted, the default is client_secret_basic -- the HTTP Basic Authentication Scheme specified in Section 2.3.1 of OAuth 2.0 [RFC6749].
     *
     **/

    CLIENT_SECRET_BASIC("client_secret_basic"), // Default Auth Method

    CLIENT_SECRET_POST("client_secret_post"),

    CLIENT_SECRET_JWT("client_secret_jwt"),

    PRIVATE_KEY_JWT("private_key_jwt"),

    NONE("none");

    private static final Map<String, TokenEndPointAuthMethod> lookup = new HashMap<>();

    static {
        for(TokenEndPointAuthMethod authMethod: TokenEndPointAuthMethod.values()){
            lookup.put(authMethod.getValue(),authMethod);
        }
    }

    private final String value;

    TokenEndPointAuthMethod(String value){
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


    public static TokenEndPointAuthMethod getByValue(String value){
        return lookup.get(value);
    }


}
