package com.ics.common;

public final class AuthenticationRequestFields {

    public static final String SCOPE = "scope";
    public static final String RESPONSE_TYPE = "response_type";
    public static final String CLIENT_ID = "client_id";
    public static final String REDIRECT_URI = "redirect_uri";
    public static final String STATE = "state"; // RECOMMENDED


    /**
     * OPTIONAL Fields
     */

    public static final String NONCE = "nonce";
    public static final String DISPLAY = "display";
    public static final String PROMPT = "prompt";
    public static final String MAX_AGE = "max_age";
    public static final String UI_LOCALES = "ui_locales";
    public static final String ID_TOKEN_HINT = "id_token_hint";
    public static final String LOGIN_HINT = "login_hint";
    public static final String ACR_VALUES = "acr_values";

    public enum Display implements FieldContainer{

        PAGE("page"),
        POPUP("popup"),
        TOUCH("touch"),
        WAP("wap");

        private final String value;

        Display(String value) {
            this.value = value;
        }


        @Override
        public String getValue() {
            return null;
        }

        @Override
        public int getOrdinal() {
            return 0;
        }
    }

    public enum Prompt implements FieldContainer{

        LOGIN("login"),
        CONSENT("consent"),
        SELECT_ACCOUNT("select_account"),
        NONE("none"); // By Default

        private final String value;

        Prompt(String value) {
            this.value = value;
        }


        @Override
        public String getValue() {
            return null;
        }

        @Override
        public int getOrdinal() {
            return 0;
        }
    }


}
