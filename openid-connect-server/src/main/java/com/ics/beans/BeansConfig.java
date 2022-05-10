package com.ics.beans;

import com.ics.oauth2.service.ClientDetailEntityService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.endpoint.DefaultRedirectResolver;
import org.springframework.security.oauth2.provider.endpoint.RedirectResolver;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;

@Configuration
@SuppressWarnings("deprecation")
public class BeansConfig {


    private final ClientDetailEntityService clientDetailEntityService;

    public BeansConfig(ClientDetailEntityService clientDetailEntityService) {
        this.clientDetailEntityService = clientDetailEntityService;
    }

    @Bean
    public OAuth2RequestFactory auth2RequestFactory(){
        return new DefaultOAuth2RequestFactory(clientDetailEntityService);
    }

    @Bean
    public RedirectResolver redirectResolver(){
        return new DefaultRedirectResolver();
    }
}
