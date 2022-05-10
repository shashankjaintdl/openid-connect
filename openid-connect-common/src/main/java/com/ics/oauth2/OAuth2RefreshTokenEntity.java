package com.ics.oauth2;

import com.ics.oauth2.models.ClientDetailsEntity;
import com.nimbusds.jwt.JWT;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;

import javax.persistence.*;
import java.util.Date;

@Entity
@SuppressWarnings("deprecation")
public class OAuth2RefreshTokenEntity implements OAuth2RefreshToken {

    private Long id;

    private ClientDetailsEntity clientDetailsEntity;

    private JWT jwt;

    private Date expirationDate;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "client_id")
    public ClientDetailsEntity getClientDetailsEntity() {
        return clientDetailsEntity;
    }

    public void setClientDetailsEntity(ClientDetailsEntity clientDetailsEntity) {
        this.clientDetailsEntity = clientDetailsEntity;
    }

    public JWT getJwt() {
        return jwt;
    }

    public void setJwt(JWT jwt) {
        this.jwt = jwt;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    @Transient
    public String getValue() {
        return jwt.serialize();
    }
}
