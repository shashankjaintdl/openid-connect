package com.ics.openid.connect.service;

import com.ics.oauth2.models.ClientDetailsEntity;
import com.ics.oauth2.models.OAuth2AccessTokenEntity;
import com.nimbusds.jwt.JWT;
import org.springframework.security.oauth2.provider.OAuth2Request;

import java.util.Date;

@SuppressWarnings("deprecation")
public interface OIDCTokenService {

    JWT generateIdToken(ClientDetailsEntity client, OAuth2Request request, Date issueDate, String sub, OAuth2AccessTokenEntity accessToken);

    OAuth2AccessTokenEntity createRegistrationAccessToken(ClientDetailsEntity client);


}
