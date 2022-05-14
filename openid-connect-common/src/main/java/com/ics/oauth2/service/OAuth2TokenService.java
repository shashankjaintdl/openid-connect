package com.ics.oauth2.service;

import com.ics.oauth2.models.OAuth2AccessTokenEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

@SuppressWarnings("deprecation")
public interface OAuth2TokenService extends AuthorizationServerTokenServices, ResourceServerTokenServices {

    @Override
    OAuth2AccessTokenEntity createAccessToken(OAuth2Authentication authentication) throws AuthenticationException;

    @Override
    OAuth2AccessTokenEntity refreshAccessToken(String refreshToken, TokenRequest tokenRequest) throws AuthenticationException;

    @Override
    OAuth2AccessTokenEntity getAccessToken(OAuth2Authentication authentication);

    @Override
    OAuth2Authentication loadAuthentication(String accessToken) throws AuthenticationException, InvalidTokenException;

    @Override
    OAuth2AccessTokenEntity readAccessToken(String accessToken);
}
