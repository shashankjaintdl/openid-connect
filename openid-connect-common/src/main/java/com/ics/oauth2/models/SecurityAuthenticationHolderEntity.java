package com.ics.oauth2.models;

import com.ics.oauth2.models.convert.SerializationStringConverter;
import com.ics.oauth2.models.convert.SimpleGrantedAuthorityConverter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

// Hold and create the authentication
@Entity
@Table(name = "authentication_holder")
@SuppressWarnings("deprecation")
public class SecurityAuthenticationHolderEntity {

    private Long id;

    private String clientId;
    private boolean approved;
    private Set<String> scope;
    private String redirectUri;
    private Set<String> resourceIds;
    private Set<String> responseType;
    private Map<String, Serializable> extension;
    private Map<String, String> requestParameter;
    private Collection<GrantedAuthority> authorities;
    private SavedUserAuthentication userAuthentication;



    @Transient
    public OAuth2Authentication getOAuth2Authentication(){
        return new OAuth2Authentication(createOAuth2Request(),getUserAuthentication());
    }


    public OAuth2Request createOAuth2Request(){
        return new OAuth2Request(getRequestParameter(), getClientId(), getAuthorities(), isApproved(), getScope(), getResourceIds(), getRedirectUri(),getResponseType(), getExtension());
    }


    public void setAuthentication(OAuth2Authentication authentication){
        OAuth2Request oAuth2Request = authentication.getOAuth2Request();
        setAuthorities(new ArrayList<>(oAuth2Request.getAuthorities()));
        setResourceIds(oAuth2Request.getResourceIds());
        setApproved(oAuth2Request.isApproved());
        setResponseType(oAuth2Request.getResponseTypes());
        setScope(oAuth2Request.getScope());
        setClientId(oAuth2Request.getClientId());
        setRedirectUri(oAuth2Request.getRedirectUri());
        setRequestParameter(oAuth2Request.getRequestParameters());
        setExtension(oAuth2Request.getExtensions());

        if(authentication.getUserAuthentication() ==null){
            this.userAuthentication = new SavedUserAuthentication(authentication.getUserAuthentication());
        }
        else{
            this.userAuthentication = null;
        }

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ElementCollection
    @CollectionTable(
            name = "authentication_holder_authority",
            joinColumns = @JoinColumn(name = "owner_id")
    )
    @Convert(converter = SimpleGrantedAuthorityConverter.class)
    @Column(name = "authority")
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "authentication_holder_resource_id",
            joinColumns = {
                    @JoinColumn(name = "owner_id")
            })
    @Column(name = "resource_id")
    public Set<String> getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(Set<String> resourceIds) {
        this.resourceIds = resourceIds;
    }

    @Basic
    @Column(name = "approved")
    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "authentication_holder_response_type",
            joinColumns = @JoinColumn(name = "owner_id")
    )
    @Column(name = "response_type")
    public Set<String> getResponseType() {
        return responseType;
    }

    public void setResponseType(Set<String> responseType) {
        this.responseType = responseType;
    }

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "authentication_holder_scope",
            joinColumns = @JoinColumn(name = "owner_id")
    )
    @Column(name = "scope")
    public Set<String> getScope() {
        return scope;
    }

    public void setScope(Set<String> scope) {
        this.scope = scope;
    }

    @Basic
    @Column(name = "client_id")
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @Basic
    @Column(name = "redirect_uri")
    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }


    /**
     * Store the requested parameters
     * @return
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "authentication_holder_request_parameter",
            joinColumns = @JoinColumn(name = "owner_id")
    )
    @Column(name = "req_value")
    @MapKeyColumn(name = "param")
    public Map<String, String> getRequestParameter() {
        return requestParameter;
    }

    public void setRequestParameter(Map<String, String> requestParameter) {
        this.requestParameter = requestParameter;
    }

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "authentication_holder_extension",
            joinColumns = @JoinColumn(name = "owner_id")
    )
    @Column(name = "ext_value")
    @MapKeyColumn(name = "extension")
    @Convert(converter = SerializationStringConverter.class,attributeName = "key")
    public Map<String, Serializable> getExtension() {
        return extension;
    }

    public void setExtension(Map<String, Serializable> extension) {
        this.extension = extension;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "auth_user_id")
    public SavedUserAuthentication getUserAuthentication() {
        return userAuthentication;
    }

    public void setUserAuthentication(SavedUserAuthentication userAuthentication) {
        this.userAuthentication = userAuthentication;
    }
}
