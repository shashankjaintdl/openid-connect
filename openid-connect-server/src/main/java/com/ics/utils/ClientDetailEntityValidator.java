package com.ics.utils;


import com.google.common.base.Strings;
import com.google.common.collect.Sets;
import com.ics.common.specs.oauth2.GrantType;
import com.ics.common.specs.oauth2.ResponseType;
import com.ics.common.specs.oauth2.SubjectType;
import com.ics.common.specs.oauth2.TokenEndPointAuthMethod;
import com.ics.exception.ValidationException;
import com.ics.oauth2.models.ClientDetailsEntity;
import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jwt.JWTClaimsSet;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.Random;

import static com.ics.common.constants.RegisteredClientDetailFields.*;

public class ClientDetailEntityValidator {

     private static final String INVALID_CLIENT_METADATA = "invalid_client_metadata";

     public static final Logger LOGGER = LoggerFactory.getLogger(ClientDetailEntityValidator.class);

//    protected ClientDetailEntityValidator() {
//        throw new IllegalStateException("Client Detail Entity Validator Exception");
//    }

    public static void validateAuthMethod(ClientDetailsEntity c) throws ValidationException {

        if (c.getTokenEndPointAuthMethod()==null){
            c.setTokenEndPointAuthMethod(TokenEndPointAuthMethod.CLIENT_SECRET_BASIC);
        }

        if(c.getTokenEndPointAuthMethod()==TokenEndPointAuthMethod.CLIENT_SECRET_BASIC ||
                c.getTokenEndPointAuthMethod()==TokenEndPointAuthMethod.CLIENT_SECRET_JWT ||
                c.getTokenEndPointAuthMethod() == TokenEndPointAuthMethod.CLIENT_SECRET_POST){

            if (Strings.isNullOrEmpty(c.getClientSecret())){
                c.setClientSecret(Base64.encodeBase64URLSafeString(new BigInteger(256,new Random()).toByteArray()));
            }
            return;
        }

        if (c.getTokenEndPointAuthMethod() == TokenEndPointAuthMethod.PRIVATE_KEY_JWT){
            throw new ValidationException(INVALID_CLIENT_METADATA,"Private key jwt auth method does not support currently!", HttpStatus.BAD_REQUEST);
        }

        if (c.getTokenEndPointAuthMethod() == TokenEndPointAuthMethod.NONE){
            c.setClientSecret(null);
            return;
        }

        throw new ValidationException(INVALID_CLIENT_METADATA,"Requested auth method does not support", HttpStatus.BAD_REQUEST);
    }


    public static void validateResponseType(ClientDetailsEntity c){
        if (c.getResponseTypes()==null){
            c.setResponseTypes(Sets.newHashSet());
        }
    }

    public static void validateGrantType(ClientDetailsEntity c) throws ValidationException{

        if (c.getAuthorizedGrantTypes().isEmpty()){
            if(c.getAuthorizedGrantTypes().contains("offline_access")){
                c.setGrantTypes(Sets.newHashSet(GrantType.AUTHORIZATION_CODE.getValue(),GrantType.REFRESH_TOKEN.getValue()));
            }
            else{
                c.setGrantTypes(Sets.newHashSet(GrantType.AUTHORIZATION_CODE.getValue()));
            }
        }

        if (c.getGrantTypes().contains(GrantType.AUTHORIZATION_CODE.getValue())){
            if (c.getGrantTypes().contains(GrantType.IMPLICIT.getValue())){
                throw new ValidationException(INVALID_CLIENT_METADATA,"",HttpStatus.BAD_REQUEST);
            }

            c.setResponseTypes(Sets.newHashSet(ResponseType.CODE.getValue()));
        }

    }

    public static void validateSoftwareStatement(ClientDetailsEntity c) throws ValidationException {

        if (c.getSoftwareStatement()==null){
            return;
        }
        try {
            JWTClaimsSet claimsSet = c.getSoftwareStatement().getJWTClaimsSet();

            for (String claim:claimsSet.getClaims().keySet()){

                switch (claim){

                    case SOFTWARE_STATEMENT:{
                        throw new ValidationException(INVALID_CLIENT_METADATA,"Software statement can't include/append/override with other software statement",HttpStatus.BAD_REQUEST);
                    }

                    case CLIENT_ID:{
                        throw new ValidationException(INVALID_CLIENT_METADATA,"Software statement can't include client Id",HttpStatus.BAD_REQUEST);
                    }

                    case CLIENT_SECRET:{
                        throw new ValidationException(INVALID_CLIENT_METADATA,"Software statement can't include client secret",HttpStatus.BAD_REQUEST);
                    }

                    case CLIENT_SECRET_ISSUED_AT:{
                        throw new ValidationException(INVALID_CLIENT_METADATA,"Software statement can't include client secret issued at",HttpStatus.BAD_REQUEST);
                    }

                    case CLIENT_SECRET_EXPIRES_AT:{
                        throw new ValidationException(INVALID_CLIENT_METADATA,"Software statement can't include client secret expires at",HttpStatus.BAD_REQUEST);
                    }

                    case CLIENT_ID_ISSUED_AT:{
                        throw new ValidationException(INVALID_CLIENT_METADATA,"Software statement can't include client id issued at",HttpStatus.BAD_REQUEST);
                    }

                    case CLIENT_REGISTRATION_URI:{
                        throw new ValidationException(INVALID_CLIENT_METADATA,"Software statement can't include client configuration endpoint",HttpStatus.BAD_REQUEST);
                    }

                    case REGISTRATION_ACCESS_TOKEN:{
                        throw new ValidationException(INVALID_CLIENT_METADATA,"Software statement can't include client registration access token",HttpStatus.BAD_REQUEST);
                    }

                    case REDIRECT_URIS:{
                        c.setRegisteredRedirectUri(Sets.newHashSet(claimsSet.getStringListClaim(claim)));
                        break;
                    }

                    case REQUEST_URIS:{
                        c.setRequestUris(Sets.newHashSet(claimsSet.getStringListClaim(claim)));
                        break;
                    }

                    case INITIATE_LOGIN_URI:{
                        c.setInitiateLoginUri(claimsSet.getStringClaim(claim));
                        break;
                    }

                    case DEFAULT_ACR_VALUES:{
                        c.setDefaultACRvalues(Sets.newHashSet(claimsSet.getStringListClaim(claim)));
                        break;
                    }

                    case REQUIRE_AUTH_TIME:{
                        c.setRequireAuthTime(claimsSet.getBooleanClaim(claim));
                        break;
                    }

                    case DEFAULT_MAX_AGE:{
                        c.setDefaultMaxAge(claimsSet.getIntegerClaim(claim));
                        break;
                    }

                    case POLICY_URI:{
                        c.setPolicyUri(claimsSet.getStringClaim(claim));
                        break;
                    }

                    case SCOPE:{
                        c.setScope(Sets.newHashSet(claimsSet.getStringListClaim(claim)));
                        break;
                    }

                    case GRANT_TYPE:{
                        c.setGrantTypes(Sets.newHashSet(claimsSet.getStringListClaim(claim)));
                        break;
                    }

                    case RESPONSE_TYPES:{
                        c.setResponseTypes(Sets.newHashSet(claimsSet.getStringListClaim(claim)));
                        break;
                    }

                    case CONTACTS:{
                        c.setContacts(Sets.newHashSet(claimsSet.getStringListClaim(claim)));
                        break;
                    }

                    case JWKS_URI:{
                        c.setJwksUri(claimsSet.getStringClaim(claim));
                        break;
                    }

                    case JWK_SET:{
                        c.setJwkSet(JWKSet.parse(claimsSet.getJSONObjectClaim(claim)));
                        break;
                    }

                    case TOS_URI:{
                        c.setTosUri(claimsSet.getStringClaim(claim));
                        break;
                    }

                    case LOGO_URI:{
                        c.setLogoUri(claimsSet.getStringClaim(claim));
                        break;
                    }

                    case CLIENT_NAME:{
                        c.setClientName(claimsSet.getStringClaim(claim));
                        break;
                    }

                    case SUBJECT_TYPE:{
                        c.setSubjectType(SubjectType.getByValue(claimsSet.getStringClaim(claim)));
                        break;
                    }

                    case TOKEN_ENDPOINT_AUTH_METHOD:{
                        c.setTokenEndPointAuthMethod(TokenEndPointAuthMethod.getByValue(claimsSet.getStringClaim(claim)));
                        break;
                    }

                    case TOKEN_ENDPOINT_AUTH_SIGNED_RESPONSE_ALG:{
                        c.setTokenEndpointAuthSignedAlg(JWSAlgorithm.parse(claimsSet.getStringClaim(claim)));
                        break;
                    }

                    case ID_TOKEN_ENCRYPTED_RESPONSE_ALG:{
                        c.setIdTokenEncryptedResponseAlg(JWEAlgorithm.parse(claimsSet.getStringClaim(claim)));
                        break;
                    }

                    case ID_TOKEN_ENCRYPTED_RESPONSE_ENC:{
                        c.setIdTokenEncryptedResponseEnc(EncryptionMethod.parse(claimsSet.getStringClaim(claim)));
                        break;
                    }

                    case ID_TOKEN_SIGNED_RESPONSE_ALG:{
                        c.setIdTokenSignedResponseAlg(JWSAlgorithm.parse(claimsSet.getStringClaim(claim)));
                        break;
                    }

                    case USERINFO_SIGNED_RESPONSE_ALG:{
                        c.setUserInfoSignedResponseAlg(JWSAlgorithm.parse(claimsSet.getStringClaim(claim)));
                        break;
                    }

                    case USERINFO_ENCRYPTED_RESPONSE_ALG:{
                        c.setUserInfoEncryptedResponseAlg(JWEAlgorithm.parse(claimsSet.getStringClaim(claim)));
                        break;
                    }

                    case USERINFO_ENCRYPTED_RESPONSE_ENC:{
                        c.setUserInfoEncryptedResponseEnc(EncryptionMethod.parse(claimsSet.getStringClaim(claim)));
                        break;
                    }

                    case REQUEST_OBJECT_SIGNED_RESPONSE_ALG:{
                        c.setRequestObjectSignedResponseAlg(JWSAlgorithm.parse(claimsSet.getStringClaim(claim)));
                        break;
                    }

                    case REQUEST_OBJECT_ENCRYPTED_RESPONSE_ALG:{
                        c.setRequestObjectEncryptedResponseAlg(JWEAlgorithm.parse(claimsSet.getStringClaim(claim)));
                        break;
                    }

                    case REQUEST_OBJECT_ENCRYPTED_RESPONSE_ENC:{
                        c.setRequestObjectEncryptedResponseEnc(EncryptionMethod.parse(claimsSet.getStringClaim(claim)));
                        break;
                    }


                    default:{
                        LOGGER.warn("Software statement can't deal or process with unknown fields/claim {} with value {}", claim,claimsSet.getClaim(claim));
                        break;
                    }

                }

            }
        }

        catch (ParseException e) {
            throw new ValidationException(INVALID_CLIENT_METADATA,"Software statement unable to parse claims",HttpStatus.BAD_REQUEST);
        }


    }

}


