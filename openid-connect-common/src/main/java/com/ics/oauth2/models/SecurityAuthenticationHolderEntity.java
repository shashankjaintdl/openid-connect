package com.ics.oauth2.models;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class SecurityAuthenticationHolderEntity {

    private Long id;

    private Collection<GrantedAuthority> authorities;

    private Set<String> resourceIds;

    private boolean approved;

    private Set<String> responseType;

    private Set<String> scope;

    private String clientId;

    private String redirectUri;

    private Map<String, String> requestParameter;

    private Map<String, Serializable> extension;




}
