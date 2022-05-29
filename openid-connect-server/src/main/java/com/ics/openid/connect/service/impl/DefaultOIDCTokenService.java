package com.ics.openid.connect.service.impl;

import com.google.common.collect.Sets;
import com.ics.oauth2.models.ClientDetailsEntity;
import com.ics.oauth2.models.OAuth2AccessTokenEntity;
import com.ics.openid.connect.service.OIDCTokenService;
import com.nimbusds.jwt.JWT;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;

@Service
@SuppressWarnings("deprecation")
public class DefaultOIDCTokenService implements OIDCTokenService {

    @Override
    public JWT generateIdToken(ClientDetailsEntity client, OAuth2Request request, Date issueDate, String sub, OAuth2AccessTokenEntity accessToken) {
        return null;
    }

    @Override
    public OAuth2AccessTokenEntity createRegistrationAccessToken(ClientDetailsEntity client) {
        return createAssociatedToken(client, Sets.newHashSet("REGISTERED_CLIENT"));
    }

    private OAuth2AccessTokenEntity createAssociatedToken(ClientDetailsEntity client, Set<String> scope){

        OAuth2AccessTokenEntity accessToken = null;

        return accessToken;
    }
}
