package com.ics.oauth2.models;

import com.ics.oauth2.models.convert.JwtStringConverter;
import com.nimbusds.jwt.JWT;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Entity
@SuppressWarnings("deprecation")
public class OAuth2AccessTokenEntity implements OAuth2AccessToken {

    private Long id;

    private ClientDetailsEntity clientDetailsEntity;

    private String tokenType = OAuth2AccessToken.BEARER_TYPE;

    private JWT jwt;

    private OAuth2RefreshTokenEntity refreshToken;

    private Set<String> scope;

    private Date expiration;

    private Map<String, Object> additionalInformation = new HashMap<>();


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
        return additionalInformation;
    }

    public void setAdditionalInformation(Map<String, Object> additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    @Override
    @ElementCollection(fetch=FetchType.EAGER)
    @CollectionTable(
            joinColumns=@JoinColumn(name="owner_id"),
            name="token_scope"
    )
    public Set<String> getScope() {
        return scope;
    }

    public void setScope(Set<String> scope){
        this.scope = scope;
    }

    @Override
    @ManyToOne
    @JoinColumn(name = "refresh_token_id")
    public OAuth2RefreshTokenEntity getRefreshToken() {
        return refreshToken;
    }


    public void setRefreshToken(OAuth2RefreshTokenEntity  refreshToken){
        this.refreshToken = refreshToken;
    }

    public void setRefreshToken(OAuth2RefreshToken refreshToken){
        if(!(refreshToken instanceof OAuth2RefreshTokenEntity)){
            throw new IllegalArgumentException("");
        }
        setRefreshToken((OAuth2RefreshTokenEntity) refreshToken);
    }

    @ManyToOne
    @JoinColumn(name = "client_id")
    public ClientDetailsEntity getClientDetailsEntity() {
        return clientDetailsEntity;
    }

    public void setClientDetailsEntity(ClientDetailsEntity clientDetailsEntity) {
        this.clientDetailsEntity = clientDetailsEntity;
    }

    @Basic
    @Override
    @Column(name = "token_type")
    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType){
        this.tokenType = tokenType;
    }


    @Override
    @Transient
    public boolean isExpired() {
        return getExpiration() != null && (System.currentTimeMillis() > getExpiration().getTime());
    }

    @Override
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expiration")
    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    @Override
    @Transient
    public int getExpiresIn() {
        if (getExpiration()==null){
            return -1;
        }
        else{
            int secondsRemaining =  (int) ((getExpiration().getTime() - System.currentTimeMillis())/1000);
            if(isExpired()){
                return 0;
            }
            return secondsRemaining;
        }
    }


    @Override
    @Transient
    public String getValue() {
        return jwt.serialize();
    }

    @Basic
    @Convert(converter = JwtStringConverter.class)
    @Column(name = "token_value")
    public JWT getJwt() {
        return jwt;
    }

    public void setJwt(JWT jwt) {
        this.jwt = jwt;
    }


}
