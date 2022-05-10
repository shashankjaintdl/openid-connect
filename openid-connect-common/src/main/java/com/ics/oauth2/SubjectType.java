package com.ics.oauth2;


import com.ics.common.FieldContainer;

import java.util.HashMap;
import java.util.Map;

public enum SubjectType implements FieldContainer {

    PAIRWISE("pairwise"),

    PUBLIC("public");

    private static final Map<String, SubjectType> lookup = new HashMap<>();

    static {
        for(SubjectType s: SubjectType.values()){
            lookup.put(s.getValue(),s);
        }
    }

    public final String value;

    SubjectType(String value){
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

    public static SubjectType getByValue(String value){
        return lookup.get(value);
    }
}
