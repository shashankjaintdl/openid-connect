package com.ics.oauth2.models;

import com.google.common.collect.ImmutableSet;
import com.ics.oauth2.AppType;
import com.ics.oauth2.SubjectType;
import com.ics.oauth2.TokenEndPointAuthMethod;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class ClientDetailEntityTest {

    @Test
    void testClientDetailsEntity(){

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_AD"));
        ClientDetailsEntity clientDetailsEntity = new ClientDetailsEntity();

        clientDetailsEntity.setId(2L);
        clientDetailsEntity.setClientId("1233qaexq");
        clientDetailsEntity.setClientSecret("54xnehtg5qku63ns7jii827ols_Su37sbz");
        clientDetailsEntity.setAccessTokenValiditySeconds(600);
        clientDetailsEntity.setRefreshTokenValiditySeconds(600);
        clientDetailsEntity.setRegisteredRedirectUri(ImmutableSet.of("https://www.example1.com/callback","https://example2.com/api/callback"));
        clientDetailsEntity.setLogoUri("https://www.examples.com/logo.png");
        clientDetailsEntity.setClientName("MyClient");
        clientDetailsEntity.setJwksUri("https://example.com/oauth2/public_secret_key.jwks");
        clientDetailsEntity.setTokenEndPointAuthMethod(TokenEndPointAuthMethod.CLIENT_SECRET_BASIC);
        clientDetailsEntity.setSubjectType(SubjectType.PAIRWISE);
        clientDetailsEntity.setAuthorities(authorities);
        clientDetailsEntity.setApplicationType(AppType.WEB);

        assertEquals(2,clientDetailsEntity.getId());
        assertEquals("1233qaexq",clientDetailsEntity.getClientId());
        assertEquals("54xnehtg5qku63ns7jii827ols_Su37sbz",clientDetailsEntity.getClientSecret());
        assertEquals(600,clientDetailsEntity.getAccessTokenValiditySeconds());
        assertEquals("https://www.examples.com/logo.png",clientDetailsEntity.getLogoUri());
        assertEquals("MyClient",clientDetailsEntity.getClientName());
        assertEquals("https://example.com/oauth2/public_secret_key.jwks",clientDetailsEntity.getJwksUri());
        assertEquals(ImmutableSet.of("https://www.example1.com/callback","https://example2.com/api/callback"),clientDetailsEntity.getRegisteredRedirectUri());
        assertEquals(TokenEndPointAuthMethod.CLIENT_SECRET_BASIC,clientDetailsEntity.getTokenEndPointAuthMethod());
        assertEquals(SubjectType.PAIRWISE,clientDetailsEntity.getSubjectType());
        assertEquals(authorities,clientDetailsEntity.getAuthorities());
        assertEquals(AppType.WEB,clientDetailsEntity.getApplicationType());

    }

}
