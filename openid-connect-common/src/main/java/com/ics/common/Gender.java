package com.ics.common;

import java.util.HashMap;
import java.util.Map;

public enum Gender implements FieldContainer{


    MALE("male"),
    FEMALE("female");

    private final String value;
    private static final Map<String, Gender> lookup = new HashMap<>();

    static {
        for (Gender g:Gender.values()){
            lookup.put(g.getValue(),g);
        }
    }

    Gender(String value) {
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

    public static Gender getByValue(String value){
        return lookup.get(value);
    }

}
