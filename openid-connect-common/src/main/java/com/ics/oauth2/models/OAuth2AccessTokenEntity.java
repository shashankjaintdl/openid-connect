package com.ics.oauth2.models;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;

import javax.persistence.*;
import java.util.Date;
import java.util.Map;
import java.util.Set;

@Entity
@SuppressWarnings("deprecation")
public class OAuth2AccessTokenEntity implements OAuth2AccessToken {

    private Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    @Transient
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }

    @Override
    @ElementCollection(fetch=FetchType.EAGER)
    @CollectionTable(
            joinColumns=@JoinColumn(name="owner_id"),
            name="token_scope"
    )
    public Set<String> getScope() {
        return null;
    }

    @Override
    public OAuth2RefreshToken getRefreshToken() {
        return null;
    }

    @Override
    public String getTokenType() {
        return null;
    }

    @Override
    public boolean isExpired() {
        return false;
    }

    @Override
    public Date getExpiration() {
        return null;
    }

    @Override
    public int getExpiresIn() {
        return 0;
    }

    @Override
    public String getValue() {
        return null;
    }
}
