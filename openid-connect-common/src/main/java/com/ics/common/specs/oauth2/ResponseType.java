package com.ics.common.specs.oauth2;

import com.ics.common.FieldContainer;

import java.util.HashMap;
import java.util.Map;

public enum ResponseType implements FieldContainer {

    CODE("code"),

    TOKEN("token"),

    ID_TOKEN("id_token");

    private static Map<String, ResponseType> lookup = new HashMap<>();

    static {
        for(ResponseType r: ResponseType.values()){
            lookup.put(r.getValue(), r);
        }
    }

    private final String value;

    ResponseType(String value){
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

    public static ResponseType getByValue(String value){
        return lookup.get(value);
    }


}
