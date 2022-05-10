package com.ics.common;

import java.util.HashMap;
import java.util.Map;

public enum Scope implements FieldContainer{

    OPENID("openid"),
    PROFILE("profile"),
    EMAIL("email"),
    ADDRESS("address"),
    PHONE("phone");

    private final String value;

    private static final Map<String, Scope> lookup = new HashMap<>();

    static {

        for (Scope  s: Scope.values()){
            lookup.put(s.getValue(),s);
        }

    }

    Scope(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public int getOrdinal() {
        return ordinal();
    }

    public static Scope getByValue(String value){
        return lookup.get(value);
    }

}
