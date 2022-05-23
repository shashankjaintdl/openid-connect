package com.ics.common.constants;

public final class RegisteredClientDetailFields {

     public static final String CLIENT_ID = "client_id";
     public static final String CLIENT_NAME = "client_name";
     public static final String CLIENT_SECRET = "client_secret";
     public static final String CLIENT_DESCRIPTION = "client_description";
     public static final String JWK_SET = "jwk_set";
     public static final String JWKS_URI = "jwks_uri";
     public static final String LOGO_URI = "logo_uri";
     public static final String POLICY_URI = "policy_uri";
     public static final String GRANT_TYPE = "grant_types";
     public static final String RESPONSE_TYPES = "response_types";
     public static final String SOFTWARE_ID = "software_id";
     public static final String SOFTWARE_VERSION = "software_version";
     public static final String CONTACTS = "contacts";
     public static final String TOKEN_ENDPOINT_AUTH_METHOD = "token_endpoint_auth_method";
     public static final String ACCESS_TOKEN_VALIDITY_SECONDS = "access_token_validity_seconds";
     public static final String REFRESH_TOKEN_VALIDITY_SECONDS = "refresh_token_validity_seconds";
     public static final String RESOURCE_IDS = "resource_ids";
     public static final String SCOPE = "scope";
     public static final String REDIRECT_URIS = "redirect_uris";
     public static final String ADDITIONAL_INFORMATION = "additional_information";
     public static final String AUTHORITIES = "authorities";
     public static final String TOS_URI = "tos_uri";
     public static final String APPLICATION_TYPE = "application_type";
     public static final String SUBJECT_TYPE = "subject_type";
     public static final String SECTOR_IDENTIFIER_URI = "sector_identifier_uri";

    /**
     *  ID TOKEN
     */

      public static final String ID_TOKEN_SIGNED_RESPONSE_ALG = "id_token_signed_response_alg";
      public static final String ID_TOKEN_ENCRYPTED_RESPONSE_ALG = "id_token_encrypted_response_alg";
      public static final String ID_TOKEN_ENCRYPTED_RESPONSE_ENC = "id_token_encrypted_response_enc";

    /**
     * USER INFO
     */

      public static final String USERINFO_SIGNED_RESPONSE_ALG = "userinfo_signed_response_alg";
      public static final String USERINFO_ENCRYPTED_RESPONSE_ALG = "userinfo_encrypted_response_alg";
      public static final String USERINFO_ENCRYPTED_RESPONSE_ENC = "userinfo_encrypted_response_enc";

    /**
     *  Request Object
     */

      public static final String REQUEST_OBJECT_SIGNED_RESPONSE_ALG = "request_object_signed_response_alg";
      public static final String REQUEST_OBJECT_ENCRYPTED_RESPONSE_ALG = "request_object_encrypted_response_alg";
      public static final String REQUEST_OBJECT_ENCRYPTED_RESPONSE_ENC = "request_object_encrypted_response_enc";

      public static final String TOKEN_ENDPOINT_AUTH_SIGNED_RESPONSE_ALG = "token_endpoint_auth_signing_alg";

      public static final String DEFAULT_MAX_AGE = "default_max_age";
      public static final String REQUIRE_AUTH_TIME = "require_auth_time";
      public static final String DEFAULT_ACR_VALUES = "default_acr_values";
      public static final String INITIATE_LOGIN_URI = "initiate_login_uri";
      public static final String REQUEST_URIS = "request_uris";

      public static final String ID_TOKEN_VALIDITY_IN_SECONDS = "id_token_validity_in_seconds";
      public static final String REUSE_REFRESH_TOKEN = "reuse_refresh_token";
      public static final String DYNAMICALLY_REGISTERED = "dynamically_registered";
      public static final String CREATED_AT = "created_at";

      public static final String SCOPE_SEPARATOR = " ";

      public static final String SOFTWARE_STATEMENT = "software_statement";


      private RegisteredClientDetailFields(){}




}
