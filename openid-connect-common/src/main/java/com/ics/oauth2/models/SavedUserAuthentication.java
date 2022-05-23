package com.ics.oauth2.models;

import com.ics.oauth2.models.convert.SimpleGrantedAuthorityConverter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "saved_auth_user")
public class SavedUserAuthentication implements Authentication {

    private Long id;
    private String name;
    private boolean authenticated;
    private String sourceClass;
    private Collection<GrantedAuthority> authorities;

    public SavedUserAuthentication(){}

    public SavedUserAuthentication(Authentication authentication){

        setName(authentication.getName());
        setAuthenticated(authentication.isAuthenticated());
        setAuthorities(new ArrayList<>(authentication.getAuthorities()));

        if(authentication instanceof SavedUserAuthentication){
            setSourceClass(((SavedUserAuthentication) authentication).getSourceClass());
        }
        else {
            setSourceClass(authentication.getClass().getName());
        }

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Override
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    @Override
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "saved_user_authorities",
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

    @Override
    @Transient
    public Object getCredentials() {
        return "";
    }

    @Override
    @Transient
    public Object getDetails() {
        return null;
    }

    @Override
    @Transient
    public Object getPrincipal() {
        return getName();
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    @Basic
    @Column(name = "source_class")
    public String getSourceClass() {
        return sourceClass;
    }

    public void setSourceClass(String sourceClass) {
        this.sourceClass = sourceClass;
    }

}
