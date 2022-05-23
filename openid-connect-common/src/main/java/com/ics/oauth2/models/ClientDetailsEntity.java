package com.ics.oauth2.models;


import com.ics.common.specs.oauth2.AppType;
import com.ics.common.specs.oauth2.SubjectType;
import com.ics.common.specs.oauth2.TokenEndPointAuthMethod;
import com.ics.oauth2.models.convert.*;
import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jwt.JWT;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;


@Entity
@Table(name = "client_details")
@SuppressWarnings( "deprecation" )
public class ClientDetailsEntity implements Serializable, ClientDetails {

    private static final long serialVersionUID = 1905122041950251207L;

    private static final int DEFAULT_ID_TOKEN_VALIDITY_IN_SECONDS = 300;

    private Long id;

    /**
     *
     *   OAuth2 Dynamic Registration Parameter or Specifications
     *
     */

    private String clientId = null; // Always Unique
    private String clientSecret = null;
    private String clientName = null;
    private String clientDescription = "";
    private JWKSet jwkSet;
    private String jwksUri;
    private String logoUri;
    private String policyUri;
    private Set<String> grantTypes = new HashSet<>();
    private Set<String> responseTypes = new HashSet<>();
    private String softwareId;
    private String softwareVersion;
    private Set<String> contacts;  // Array of e-mail addresses of people responsible for this Client
    private TokenEndPointAuthMethod tokenEndPointAuthMethod = TokenEndPointAuthMethod.CLIENT_SECRET_BASIC;

    /**
     * Supported Field in Default Client Details Interface
     */

    private Integer accessTokenValiditySeconds = 0;
    private Integer refreshTokenValiditySeconds = 0;
    private Set<String> resourceIds = new HashSet<>();
    private Set<String> scope = new HashSet<>();
    private Set<String> registeredRedirectUri = new HashSet<>();
    private Map<String, Object> additionalInformation = new HashMap<>();
    private Collection<GrantedAuthority> authorities = new HashSet<>();

    /**
     *  OIDC Fields
     */

    private String tosUri; // tos_uri
    private AppType applicationType; // Application Type
    private SubjectType subjectType; // Subject Type like "PAIRWISE" and "PUBLIC"
    private String sectorIdentifierUri; // sector_identifier_uri

    private JWSAlgorithm idTokenSignedResponseAlg; // id_token_signed_response_alg
    private JWEAlgorithm idTokenEncryptedResponseAlg; // id_token_encrypted_response_alg
    private EncryptionMethod idTokenEncryptedResponseEnc; // id_token_encrypted_response_enc

    private JWSAlgorithm userInfoSignedResponseAlg; // userinfo_signed_response_alg
    private JWEAlgorithm userInfoEncryptedResponseAlg; // userinfo_encrypted_response_alg
    private EncryptionMethod userInfoEncryptedResponseEnc; // userinfo_encrypted_response_enc

    private JWSAlgorithm requestObjectSignedResponseAlg;  // request_object_response_alg
    private JWEAlgorithm requestObjectEncryptedResponseAlg; // request_object_encrypted_response_alg
    private EncryptionMethod requestObjectEncryptedResponseEnc; // request_object_encryption_enc

    private JWSAlgorithm tokenEndpointAuthSignedAlg; // token_endpoint_auth_signing_alg

    private Integer defaultMaxAge; // default_max_age
    private Boolean requireAuthTime; // require_auth_time
    private Set<String> defaultACRvalues; // default_acr_value

    private String initiateLoginUri; // initiate_login_uri
    private Set<String> requestUris; // request_uris

    /**
     *  Custom Fields
     */

    private Integer idTokenValidityInSeconds;
    private boolean reuseRefreshToken;
    private boolean dynamicallyRegistered;
    private Date createdAt;

    /** Software statement **/
    private JWT softwareStatement;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    /**
     * Dynamic OAuth2 Client Registration Parameter's Getter/Setter Methods
     */

    @Override
    @Column(name = "client_id", unique = true)
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId){
        this.clientId = clientId;
    }


    @Override
    @Column(name = "client_secret")
    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret){
        this.clientSecret = clientSecret;
    }

    @Override
    @Transient
    public boolean isSecretRequired() {
        return false;
    }

    @Column(name = "client_name")
    public String getClientName(){
        return clientName;
    }

    public void setClientName(String clientName){
        this.clientName = clientName;
    }

    @Column(name = "client_description")
    public String getClientDescription() {
        return clientDescription;
    }

    public void setClientDescription(String clientDescription) {
        this.clientDescription = clientDescription;
    }

    @Column(name = "jwks")
    @Convert(converter = JWKSetStringConverter.class)
    public JWKSet getJwkSet(){
        return jwkSet;
    }

    public void setJwkSet(JWKSet jwkSet){
        this.jwkSet = jwkSet;
    }

    @Column(name = "jwks_uri")
    public String getJwksUri(){
        return jwksUri;
    }

    public void setJwksUri(String jwksUri){
        this.jwksUri = jwksUri;
    }

    @Column(name = "logo_uri")
    public String getLogoUri(){
        return logoUri;
    }

    public void setLogoUri(String logoUri){
        this.logoUri = logoUri;
    }

    @Column(name = "policy_uri")
    public String getPolicyUri(){
        return policyUri;
    }

    public void setPolicyUri(String policyUri){
        this.policyUri = policyUri;
    }

    @Column(name = "response_type")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "client_response_type",
            joinColumns = @JoinColumn(name = "owner_id")
    )
    public Set<String> getResponseTypes(){
        return responseTypes;
    }

    public void setResponseTypes(Set<String> responseTypes){
        this.responseTypes = responseTypes;
    }

    @Column(name = "software_id")
    public String getSoftwareId() {
        return softwareId;
    }

    public void setSoftwareId(String softwareId) {
        this.softwareId = softwareId;
    }

    @Column(name = "software_version")
    public String getSoftwareVersion() {
        return softwareVersion;
    }

    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion;
    }

    @Column(name = "contact")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "client_contact",
            joinColumns = @JoinColumn(name = "owner_id")
    )
    public Set<String> getContacts() {
        return contacts;
    }

    public void setContacts(Set<String> contacts) {
        this.contacts = contacts;
    }


    @Override
    @Column(name = "resource_ids")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "client_resource",
            joinColumns = @JoinColumn(name = "owner_id")
    )
    public Set<String> getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(Set<String> resourceIds){
        this.resourceIds = resourceIds;
    }


    @Override
    @Transient
    public boolean isScoped() {
        return getScope()!=null && !getScope().isEmpty();
    }

    public void setScope(Set<String> scope) {
        this.scope = scope;
    }

    @Override
    @Column(name = "scope")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "client_scope",
            joinColumns = @JoinColumn(name = "owner_id")
    )
    public Set<String> getScope() {
        return scope;
    }

    @Column(name = "grant_type")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "client_grant_type",
            joinColumns = @JoinColumn(name = "owner_id")
    )
    public Set<String> getGrantTypes(){
        return grantTypes;
    }

    public void setGrantTypes(Set<String> grantTypes){
        this.grantTypes = grantTypes;
    }

    @Override
    @Transient
    public Set<String> getAuthorizedGrantTypes() {
        return getGrantTypes();
    }


    @Override
    @Column(name = "redirect_uri")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "client_redirect_uri",
            joinColumns = @JoinColumn(name = "owner_id")
    )
    public Set<String> getRegisteredRedirectUri() {
        return registeredRedirectUri;
    }


    public void setRegisteredRedirectUri(Set<String> registeredRedirectUri){
        this.registeredRedirectUri = registeredRedirectUri;
    }


    @Override
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "client_authority",
            joinColumns = @JoinColumn(name = "owner_id")
    )
    @Convert(converter = SimpleGrantedAuthorityConverter.class)
    @Column(name = "authority")
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<GrantedAuthority> authorities){
        this.authorities = authorities;
    }

    @Basic
    @Override
    @Column(name = "access_token_validity_seconds")
    public Integer getAccessTokenValiditySeconds() {
        return accessTokenValiditySeconds;
    }

    public void setAccessTokenValiditySeconds(Integer accessTokenValiditySeconds){
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
    }


    @Basic
    @Override
    @Column(name = "refresh_token_validity_seconds")
    public Integer getRefreshTokenValiditySeconds() {
        return refreshTokenValiditySeconds;
    }

    public void setRefreshTokenValiditySeconds(Integer refreshTokenValiditySeconds){
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
    }

    @Override
    public boolean isAutoApprove(String scope) {
        return false;
    }


    @Override
    @Transient()
    public Map<String, Object> getAdditionalInformation() {
        return additionalInformation;
    }


    public void setAdditionalInformation(Map<String, Object> additionalInformation){
        this.additionalInformation = additionalInformation;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "token_endpoint_auth_method")
    public TokenEndPointAuthMethod getTokenEndPointAuthMethod() {
        return tokenEndPointAuthMethod;
    }

    public void setTokenEndPointAuthMethod(TokenEndPointAuthMethod tokenEndPointAuthMethod) {
        this.tokenEndPointAuthMethod = tokenEndPointAuthMethod;
    }



    /**
     *  OIDC Getter/Setter Methods
     */


    @Basic
    @Column(name = "id_token_validity_in_seconds")
    public Integer getIdTokenValidityInSeconds() {
        return idTokenValidityInSeconds;
    }

    public void setIdTokenValidityInSeconds(Integer idTokenValidityInSeconds) {
        this.idTokenValidityInSeconds = idTokenValidityInSeconds;
    }

    @Column(name = "tos_uri")
    public String getTosUri() {
        return tosUri;
    }

    public void setTosUri(String tosUri) {
        this.tosUri = tosUri;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "application_type")
    public AppType getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(AppType applicationType) {
        this.applicationType = applicationType;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "subject_type")
    public SubjectType getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(SubjectType subjectType) {
        this.subjectType = subjectType;
    }

    @Column(name = "request_uri")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "client_request_uri",
            joinColumns = @JoinColumn(name = "owner_id")
    )
    public Set<String> getRequestUris() {
        return requestUris;
    }

    public void setRequestUris(Set<String> requestUris) {
        this.requestUris = requestUris;
    }

    @Basic
    @Column(name = "sector_identifier_uri")
    public String getSectorIdentifierUri() {
        return sectorIdentifierUri;
    }

    public void setSectorIdentifierUri(String sectorIdentifierUri) {
        this.sectorIdentifierUri = sectorIdentifierUri;
    }

    @Basic
    @Column(name="default_max_age")
    public Integer getDefaultMaxAge() {
        return defaultMaxAge;
    }

    public void setDefaultMaxAge(Integer defaultMaxAge) {
        this.defaultMaxAge = defaultMaxAge;
    }

    @Basic
    @Column(name="require_auth_time")
    public Boolean getRequireAuthTime() {
        return requireAuthTime;
    }

    public void setRequireAuthTime(Boolean requireAuthTime) {
        this.requireAuthTime = requireAuthTime;
    }


    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name="client_default_acr_value",
            joinColumns=@JoinColumn(name="owner_id")
    )
    @Column(name="default_acr_value")
    public Set<String> getDefaultACRvalues() {
        return defaultACRvalues;
    }


    public void setDefaultACRvalues(Set<String> defaultACRvalues) {
        this.defaultACRvalues = defaultACRvalues;
    }

    @Basic
    @Column(name = "initiate_login_uri")
    public String getInitiateLoginUri() {
        return initiateLoginUri;
    }

    public void setInitiateLoginUri(String initiateLoginUri) {
        this.initiateLoginUri = initiateLoginUri;
    }

    @Basic
    @Column(name = "reuse_refresh_token")
    public boolean isReuseRefreshToken(){
        return reuseRefreshToken;
    }

    public void setReuseRefreshToken(boolean reuseRefreshToken){
        this.reuseRefreshToken = reuseRefreshToken;
    }

    @Basic
    @Column(name = "dynamically_registered")
    public boolean isDynamicallyRegistered() {
        return dynamicallyRegistered;
    }

    public void setDynamicallyRegistered(boolean dynamicallyRegistered) {
        this.dynamicallyRegistered = dynamicallyRegistered;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at")
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Basic
    @Column(name = "token_endpoint_auth_signed_alg")
    @Convert(converter = JWSAlgorithmStringConverter.class)
    public JWSAlgorithm getTokenEndpointAuthSignedAlg() {
        return tokenEndpointAuthSignedAlg;
    }

    public void setTokenEndpointAuthSignedAlg(JWSAlgorithm tokenEndpointAuthSignedAlg) {
        this.tokenEndpointAuthSignedAlg = tokenEndpointAuthSignedAlg;
    }

    @Basic
    @Column(name = "id_token_signed_response_alg")
    @Convert(converter = JWSAlgorithmStringConverter.class)
    public JWSAlgorithm getIdTokenSignedResponseAlg() {
        return idTokenSignedResponseAlg;
    }

    public void setIdTokenSignedResponseAlg(JWSAlgorithm idTokenSignedResponseAlg) {
        this.idTokenSignedResponseAlg = idTokenSignedResponseAlg;
    }

    @Basic
    @Column(name = "id_token_encrypted_response_alg")
    @Convert(converter = JWEAlgorithmStringConverter.class)
    public JWEAlgorithm getIdTokenEncryptedResponseAlg() {
        return idTokenEncryptedResponseAlg;
    }

    public void setIdTokenEncryptedResponseAlg(JWEAlgorithm idTokenEncryptedResponseAlg) {
        this.idTokenEncryptedResponseAlg = idTokenEncryptedResponseAlg;
    }

    @Basic
    @Column(name = "id_token_encrypted_response_enc")
    @Convert(converter = JWEEncryptedMethodStringConverter.class)
    public EncryptionMethod getIdTokenEncryptedResponseEnc() {
        return idTokenEncryptedResponseEnc;
    }

    public void setIdTokenEncryptedResponseEnc(EncryptionMethod idTokenEncryptedResponseEnc) {
        this.idTokenEncryptedResponseEnc = idTokenEncryptedResponseEnc;
    }

    @Basic
    @Column(name = "userinfo_signed_response_alg")
    @Convert(converter = JWSAlgorithmStringConverter.class)
    public JWSAlgorithm getUserInfoSignedResponseAlg() {
        return userInfoSignedResponseAlg;
    }

    public void setUserInfoSignedResponseAlg(JWSAlgorithm userInfoSignedResponseAlg) {
        this.userInfoSignedResponseAlg = userInfoSignedResponseAlg;
    }

    @Basic
    @Column(name = "userinfo_encrypted_response_alg")
    @Convert(converter = JWEAlgorithmStringConverter.class)
    public JWEAlgorithm getUserInfoEncryptedResponseAlg() {
        return userInfoEncryptedResponseAlg;
    }

    public void setUserInfoEncryptedResponseAlg(JWEAlgorithm userInfoEncryptedResponseAlg) {
        this.userInfoEncryptedResponseAlg = userInfoEncryptedResponseAlg;
    }

    @Basic
    @Column(name = "userinfo_encrypted_response_enc")
    @Convert(converter = JWEEncryptedMethodStringConverter.class)
    public EncryptionMethod getUserInfoEncryptedResponseEnc() {
        return userInfoEncryptedResponseEnc;
    }

    public void setUserInfoEncryptedResponseEnc(EncryptionMethod userInfoEncryptedResponseEnc) {
        this.userInfoEncryptedResponseEnc = userInfoEncryptedResponseEnc;
    }

    @Basic
    @Column(name = "request_object_signed_response_alg")
    @Convert(converter = JWSAlgorithmStringConverter.class)
    public JWSAlgorithm getRequestObjectSignedResponseAlg() {
        return requestObjectSignedResponseAlg;
    }

    public void setRequestObjectSignedResponseAlg(JWSAlgorithm requestObjectSignedResponseAlg) {
        this.requestObjectSignedResponseAlg = requestObjectSignedResponseAlg;
    }

    @Basic
    @Column(name = "request_object_encrypted_response_alg")
    @Convert(converter = JWEAlgorithmStringConverter.class)
    public JWEAlgorithm getRequestObjectEncryptedResponseAlg() {
        return requestObjectEncryptedResponseAlg;
    }

    public void setRequestObjectEncryptedResponseAlg(JWEAlgorithm requestObjectEncryptedResponseAlg) {
        this.requestObjectEncryptedResponseAlg = requestObjectEncryptedResponseAlg;
    }

    @Basic
    @Column(name = "request_object_encrypted_response_enc")
    @Convert(converter = JWEEncryptedMethodStringConverter.class)
    public EncryptionMethod getRequestObjectEncryptedResponseEnc() {
        return requestObjectEncryptedResponseEnc;
    }

    public void setRequestObjectEncryptedResponseEnc(EncryptionMethod requestObjectEncryptedResponseEnc) {
        this.requestObjectEncryptedResponseEnc = requestObjectEncryptedResponseEnc;
    }

    @Basic
    @Column(name = "software_statement")
    @Convert(converter = JwtStringConverter.class)
    public JWT getSoftwareStatement() {
        return softwareStatement;
    }

    public void setSoftwareStatement(JWT softwareStatement) {
        this.softwareStatement = softwareStatement;
    }




}
