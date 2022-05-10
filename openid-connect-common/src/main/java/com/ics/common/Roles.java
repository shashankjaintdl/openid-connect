package com.ics.common;

public enum Roles implements FieldContainer {

    ROLE_SAD,
    ROLE_AD,
    ROLE_MGR,
    ROLE_USER;

    @Override
    public String getValue() {
        return name();
    }

    @Override
    public int getOrdinal() {
        return ordinal();
    }
}
