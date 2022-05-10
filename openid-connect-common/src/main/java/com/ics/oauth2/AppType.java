package com.ics.oauth2;


import com.ics.common.FieldContainer;

import java.util.HashMap;
import java.util.Map;

public enum AppType implements FieldContainer {

    WEB("web"),

    NATIVE("native");

    private static final  Map<String, AppType> lookup = new HashMap<>();

    static {
        for (AppType appType: AppType.values()){
            lookup.put(appType.getValue(),appType);
        }
    }

    private final String value;

    AppType(String value){
        this.value = value;
    }

    @Override
    public String getValue(){
        return value;
    }

    public int getOrdinal(){
        return ordinal();
    }


    public static AppType getByValue(String value){
        return lookup.get(value);
    }
}
