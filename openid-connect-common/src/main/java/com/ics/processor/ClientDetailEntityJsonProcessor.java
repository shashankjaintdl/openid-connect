package com.ics.processor;

import com.google.common.base.Splitter;
import com.google.common.collect.Sets;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ics.common.specs.oauth2.AppType;
import com.ics.common.specs.oauth2.SubjectType;
import com.ics.common.specs.oauth2.TokenEndPointAuthMethod;
import com.ics.oauth2.models.ClientDetailsEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.ics.oauth2.utils.JsonUtils.*;
import static com.ics.common.constants.RegisteredClientDetailFields.*;

@SuppressWarnings("deprecation")
public class ClientDetailEntityJsonProcessor {

    private static final JsonParser PARSER = new JsonParser();

    private ClientDetailEntityJsonProcessor(){
        throw new IllegalStateException("Processor Class");
    }

    public static ClientDetailsEntity parse(String s){
        JsonElement jsonElement = PARSER.parse(s);
        return jsonElement.isJsonObject() ? parse(jsonElement) : null;
    }

    private static ClientDetailsEntity parse(JsonElement jsonElement) {

        JsonObject o = jsonElement.getAsJsonObject();
        ClientDetailsEntity c = new ClientDetailsEntity();

        // Dynamically Setting Client ID and Client Secret
        c.setClientId(getAsString(o, CLIENT_ID));
        c.setClientSecret(getAsString(o, CLIENT_SECRET));
        c.setClientName(getAsString(o, CLIENT_NAME));
        c.setContacts(getAsStringSet(o, CONTACTS));
        c.setTosUri(getAsString(o, TOS_URI));
        c.setPolicyUri(getAsString(o, POLICY_URI));
        c.setClientDescription(getAsString(o, CLIENT_DESCRIPTION));

        String scope = getAsString(o, SCOPE);
        if (scope != null) {
            c.setScope(Sets.newHashSet(Splitter.on(SCOPE_SEPARATOR).split(scope)));
        }

        c.setGrantTypes(getAsStringSet(o, GRANT_TYPE));
        c.setResponseTypes(getAsStringSet(o, RESPONSE_TYPES));
        c.setLogoUri(getAsString(o, LOGO_URI));

        String subjectType = getAsString(o, SUBJECT_TYPE);
        if (subjectType != null) {
            c.setSubjectType(SubjectType.getByValue(subjectType));
        }

        c.setSectorIdentifierUri(getAsString(o, SECTOR_IDENTIFIER_URI));

        String authMethod = getAsString(o, TOKEN_ENDPOINT_AUTH_METHOD);
        if (authMethod != null) {
            c.setTokenEndPointAuthMethod(TokenEndPointAuthMethod.getByValue(authMethod));
        }

        c.setJwkSet(getAsJWKSet(o, JWK_SET));
        c.setJwksUri(getAsString(o, JWKS_URI));

        c.setRegisteredRedirectUri(getAsStringSet(o, REDIRECT_URIS));

        c.setTokenEndpointAuthSignedAlg(getAsJwsAlgorithm(o, TOKEN_ENDPOINT_AUTH_SIGNED_RESPONSE_ALG));

        c.setUserInfoSignedResponseAlg(getAsJwsAlgorithm(o, USERINFO_SIGNED_RESPONSE_ALG));
        c.setUserInfoEncryptedResponseAlg(getAsJweAlgorithm(o, USERINFO_ENCRYPTED_RESPONSE_ALG));
        c.setUserInfoEncryptedResponseEnc(getEncryptedMethod(o, USERINFO_ENCRYPTED_RESPONSE_ENC));

        c.setIdTokenSignedResponseAlg(getAsJwsAlgorithm(o, ID_TOKEN_SIGNED_RESPONSE_ALG));
        c.setIdTokenEncryptedResponseAlg(getAsJweAlgorithm(o, ID_TOKEN_ENCRYPTED_RESPONSE_ALG));
        c.setIdTokenEncryptedResponseEnc(getEncryptedMethod(o, ID_TOKEN_ENCRYPTED_RESPONSE_ENC));

        c.setRequestObjectSignedResponseAlg(getAsJwsAlgorithm(o, REQUEST_OBJECT_SIGNED_RESPONSE_ALG));
        c.setRequestObjectEncryptedResponseAlg(getAsJweAlgorithm(o, REQUEST_OBJECT_ENCRYPTED_RESPONSE_ALG));
        c.setRequestObjectEncryptedResponseEnc(getEncryptedMethod(o, REQUEST_OBJECT_ENCRYPTED_RESPONSE_ENC));

        c.setDefaultMaxAge(getAsInteger(o, DEFAULT_MAX_AGE));

        c.setRequireAuthTime(getAsBoolean(o, REQUIRE_AUTH_TIME));

        c.setDefaultACRvalues(getAsStringSet(o, DEFAULT_ACR_VALUES));
        c.setInitiateLoginUri(getAsString(o, INITIATE_LOGIN_URI));
        c.setRequestUris(getAsStringSet(o, REQUEST_URIS));

        c.setAccessTokenValiditySeconds(getAsInteger(o, ACCESS_TOKEN_VALIDITY_SECONDS));
        c.setRefreshTokenValiditySeconds(getAsInteger(o, REFRESH_TOKEN_VALIDITY_SECONDS));

        c.setResourceIds(getAsStringSet(o,RESOURCE_IDS));

        String appType = getAsString(o, APPLICATION_TYPE);
        if (appType != null) {
            c.setApplicationType(AppType.getByValue(appType));
        }

        c.setSoftwareId(getAsString(o, SOFTWARE_ID));
        c.setSoftwareVersion(getAsString(o, SOFTWARE_VERSION));

        return c;
    }





}
