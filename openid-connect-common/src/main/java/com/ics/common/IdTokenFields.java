package com.ics.common;


public final class IdTokenFields {

    private static final String ISS = "iss";
    private static final String SUB = "sub";
    private static final String AUD = "aud";
    private static final String EXP = "exp";
    private static final String IAT = "iat";
    private static final String AUTH_TIME = "auth_time";
    private static final String NONCE = "nonce";
    private static final String ACR = "acr";
    private static final String AMR = "amr";
    private static final String AZP = "azp";
    
    private IdTokenFields(){}


    public static String getISS() {
        return ISS;
    }

    public static String getSUB() {
        return SUB;
    }

    public static String getAUD() {
        return AUD;
    }

    public static String getEXP() {
        return EXP;
    }

    public static String getIAT() {
        return IAT;
    }

    public static String getAuthTime() {
        return AUTH_TIME;
    }

    public static String getNONCE() {
        return NONCE;
    }

    public static String getACR() {
        return ACR;
    }

    public static String getAMR() {
        return AMR;
    }

    public static String getAZP() {
        return AZP;
    }
}
