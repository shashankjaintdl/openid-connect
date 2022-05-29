package com.ics.oauth2.models;

import com.google.gson.JsonObject;
import com.ics.common.specs.oauth2.AppType;
import com.ics.common.specs.oauth2.SubjectType;
import com.ics.common.specs.oauth2.TokenEndPointAuthMethod;
import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jwt.JWT;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Set;

public class RegisteredClientDetails  {

    private String registeredClientAccessToken;
    private String registeredClientUri;
    private Date clientSecretExpireAt;
    private Date clientIdIssueAt;
    private ClientDetailsEntity client;
    private JsonObject jsonObject;

    public RegisteredClientDetails(){
        this.client = new ClientDetailsEntity();
    }

    public RegisteredClientDetails(ClientDetailsEntity client){
        this();
        this.client = client;
    }

    public RegisteredClientDetails(ClientDetailsEntity client, String registeredClientAccessToken, String registeredClientUri){
        this(client);
        this.registeredClientAccessToken = registeredClientAccessToken;
        this.registeredClientUri = registeredClientUri;
    }

    public String getRegisteredClientAccessToken() {
        return registeredClientAccessToken;
    }

    public void setRegisteredClientAccessToken(String registeredClientAccessToken) {
        this.registeredClientAccessToken = registeredClientAccessToken;
    }

    public String getRegisteredClientUri() {
        return registeredClientUri;
    }

    public void setRegisteredClientUri(String registeredClientUri) {
        this.registeredClientUri = registeredClientUri;
    }

    public Date getClientSecretExpireAt() {
        return clientSecretExpireAt;
    }

    public void setClientSecretExpireAt(Date clientSecretExpireAt) {
        this.clientSecretExpireAt = clientSecretExpireAt;
    }

    public Date getClientIdIssueAt() {
        return clientIdIssueAt;
    }

    public void setClientIdIssueAt(Date clientIdIssueAt) {
        this.clientIdIssueAt = clientIdIssueAt;
    }

    public String getClientId(){
        return client.getClientId();
    }

    public String getClientSecret(){
        return client.getClientSecret();
    }

    public String getClientDescription(){
        return client.getClientDescription();
    }

    public JWKSet getJwkSet(){
        return client.getJwkSet();
    }

    public String getJwksUri(){
        return client.getJwksUri();
    }

    public String getLogoUri(){
        return client.getLogoUri();
    }

    public String getPolicyUri(){
        return client.getPolicyUri();
    }

    public Set<String> getGrantType(){
        return client.getGrantTypes();
    }

    public Set<String> getResponseType(){
        return client.getResponseTypes();
    }

    public String getSoftwareId(){
        return client.getSoftwareId();
    }

    public String getSoftwareVersion(){
        return client.getSoftwareVersion();
    }

    public Set<String> getContact(){
        return client.getContacts();
    }

    public TokenEndPointAuthMethod getTokenEndpointAuthMethod(){
        return client.getTokenEndPointAuthMethod();
    }

    public int getAccessTokenValiditySeconds(){
        return client.getAccessTokenValiditySeconds();
    }

    public int getRefreshTokenValiditySeconds(){
        return client.getRefreshTokenValiditySeconds();
    }

    public int getIdTokenValidityInSeconds(){
        return client.getIdTokenValidityInSeconds();
    }

    public Set<String> getResourceIds(){
        return client.getResourceIds();
    }

    public Set<String> getScope(){
        return client.getScope();
    }

    public Set<String> getRedirectUri(){
        return client.getRegisteredRedirectUri();
    }

    public Map<String, Object> getAdditionalInformation(){
        return client.getAdditionalInformation();
    }

    public Collection<GrantedAuthority> getAuthorities(){
        return client.getAuthorities();
    }

    public String getTosUri(){
        return client.getTosUri();
    }

    public AppType getApplicationType(){
        return client.getApplicationType();
    }

    public SubjectType getSubjectType(){
        return client.getSubjectType();
    }

    public String getSectorIdentifierUri(){
        return client.getSectorIdentifierUri();
    }

    public JWSAlgorithm getIdTokenSignedResponseAlg(){
        return client.getIdTokenSignedResponseAlg();
    }

    public JWEAlgorithm getIdTokenEncryptedResponseAlg(){
        return client.getIdTokenEncryptedResponseAlg();
    }

    public EncryptionMethod getIdTokenEncryptedResponseEnc(){
        return client.getIdTokenEncryptedResponseEnc();
    }

    public JWSAlgorithm getUserInfoSignedResponseAlg(){
        return client.getUserInfoSignedResponseAlg();
    }

    public JWEAlgorithm getUserInfoEncryptedResponseAlg(){
        return client.getUserInfoEncryptedResponseAlg();
    }

    public EncryptionMethod getUserInfoEncryptedResponseEnc(){
        return client.getUserInfoEncryptedResponseEnc();
    }

    public JWSAlgorithm getRequestObjectSignedResponseAlg(){
        return client.getRequestObjectSignedResponseAlg();
    }

    public JWEAlgorithm getRequestObjectEncryptedResponseAlg(){
        return client.getRequestObjectEncryptedResponseAlg();
    }

    public EncryptionMethod getRequestObjectEncryptedResponseEnc(){
        return client.getRequestObjectEncryptedResponseEnc();
    }

    public JWSAlgorithm getTokenEndpointAuthSignedAlg(){
        return client.getTokenEndpointAuthSignedAlg();
    }

    public int getDefaultMaxAge(){
        return client.getDefaultMaxAge();
    }

    public boolean getRequireAuthTime(){
        return client.getRequireAuthTime();
    }

    public Set<String> getDefaultACRvalues(){
        return client.getDefaultACRvalues();
    }

    public String getInitiateLoginUri(){
        return client.getInitiateLoginUri();
    }

    public Set<String> getRequestUris(){
        return client.getRequestUris();
    }

    public boolean isReuseRefreshToken(){
        return client.isReuseRefreshToken();
    }

    public boolean isDynamicallyRegistered(){
        return client.isDynamicallyRegistered();
    }

    public Date getCreatedAt(){
        return client.getCreatedAt();
    }

    public JWT getSoftwareStatement(){
        return client.getSoftwareStatement();
    }

    public JsonObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
    }


}
