package com.ics.oauth2.service.impl;

import com.ics.oauth2.models.ClientDetailsEntity;
import com.ics.oauth2.models.OAuth2AccessTokenEntity;
import com.ics.oauth2.models.OAuth2RefreshTokenEntity;
import com.ics.oauth2.repository.OAuth2AccessTokenRepository;
import com.ics.oauth2.repository.OAuth2RefreshTokenRepository;
import com.ics.oauth2.repository.SecurityAuthenticationHolderRepository;
import com.ics.oauth2.service.ClientDetailEntityService;
import com.ics.oauth2.service.OAuth2TokenService;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@SuppressWarnings("deprecation")
public class DefaultOAuth2TokenService implements OAuth2TokenService {

    private final OAuth2AccessTokenRepository accessTokenRepository;

    private final OAuth2RefreshTokenRepository refreshTokenRepository;

    private final ClientDetailEntityService clientDetailEntityService;

    private final SecurityAuthenticationHolderRepository authenticationHolderRepository;

    //    private final TokenEnhancer tokenEnhancer;


    public DefaultOAuth2TokenService(OAuth2AccessTokenRepository accessTokenRepository,
                                     OAuth2RefreshTokenRepository refreshTokenRepository,
                                     ClientDetailEntityService clientDetailEntityService,
                                     SecurityAuthenticationHolderRepository authenticationHolderRepository) {
        this.accessTokenRepository = accessTokenRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.clientDetailEntityService = clientDetailEntityService;
        this.authenticationHolderRepository = authenticationHolderRepository;
    }


    @Override
    public OAuth2AccessTokenEntity createAccessToken(OAuth2Authentication authentication) throws AuthenticationException {
        return null;
    }

    @Override
    public OAuth2AccessTokenEntity refreshAccessToken(String refreshToken, TokenRequest tokenRequest) throws AuthenticationException {
        return null;
    }

    @Override
    public OAuth2AccessTokenEntity getAccessToken(OAuth2Authentication authentication) {
        return null;
    }

    @Override
    public OAuth2Authentication loadAuthentication(String accessToken) throws AuthenticationException, InvalidTokenException {
        return null;
    }

    @Override
    public OAuth2AccessTokenEntity readAccessToken(String accessToken) {
        return null;
    }

    @Override
    public List<OAuth2AccessTokenEntity> getAccessTokenByClient(ClientDetailsEntity client) {
        return null;
    }

    @Override
    public List<OAuth2RefreshTokenEntity> getRefreshTokenByClient(ClientDetailsEntity client) {
        return null;
    }

    @Override
    public OAuth2AccessTokenEntity revokeAccessToken(OAuth2AccessTokenEntity accessToken) {
        return null;
    }

    @Override
    public OAuth2RefreshTokenEntity revokeRefreshToken(OAuth2RefreshTokenEntity refreshToken) {
        return null;
    }
}
