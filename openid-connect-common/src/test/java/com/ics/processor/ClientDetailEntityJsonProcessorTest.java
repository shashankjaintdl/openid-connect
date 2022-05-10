package com.ics.processor;

import com.google.common.collect.ImmutableSet;
import com.ics.oauth2.*;
import com.ics.oauth2.models.ClientDetailsEntity;
import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JWEAlgorithm;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("deprecation")
class ClientDetailEntityJsonProcessorTest {

    @Test
    void testNullParser() throws Exception{
        String s = "";
        ClientDetailsEntity c = ClientDetailEntityJsonProcessor.parse(s);
        assertNull(c);
    }


    @Test
    void testParse() throws Exception{

        String json = "  {\n" +
                "   \"application_type\": \"web\",\n" +
                "   \"redirect_uris\":\n" +
                "     [\"https://client.example.org/callback\",\n" +
                "      \"https://client.example.org/callback2\"],\n" +
                "   \"client_name\": \"My Example\",\n" +
                "   \"client_name#ja-Jpan-JP\":\n" +
                "     \"クライアント名\",\n" +
                "   \"response_types\": [\"code\", \"token\"],\n" +
                "   \"grant_types\": [\"authorization_code\", \"implicit\"],\n" +
                "   \"logo_uri\": \"https://client.example.org/logo.png\",\n" +
                "   \"subject_type\": \"pairwise\",\n" +
                "   \"sector_identifier_uri\":\n" +
                "     \"https://other.example.net/file_of_redirect_uris.json\",\n" +
                "   \"token_endpoint_auth_method\": \"client_secret_basic\",\n" +
                "   \"jwks_uri\": \"https://client.example.org/my_public_keys.jwks\",\n" +
                "   \"userinfo_encrypted_response_alg\": \"RSA1_5\",\n" +
                "   \"userinfo_encrypted_response_enc\": \"A128CBC-HS256\",\n" +
                "   \"contacts\": [\"ve7jtb@example.org\", \"mary@example.org\"],\n" +
                "   \"request_uris\":\n" +
                "     [\"https://client.example.org/rf.txt#qpXaRLh_n93TTR9F252ValdatUQvQiJi5BDub2BeznA\"]\n" +
                "  }";

        ClientDetailsEntity c = ClientDetailEntityJsonProcessor.parse(json);

        assertEquals("My Example",c.getClientName());
        assertEquals(ImmutableSet.of(GrantType.AUTHORIZATION_CODE.getValue(),GrantType.IMPLICIT.getValue()),c.getGrantTypes());
        assertEquals(ImmutableSet.of(ResponseType.CODE.getValue(),ResponseType.TOKEN.getValue()),c.getResponseTypes());
        assertEquals("https://client.example.org/logo.png",c.getLogoUri());
        assertEquals(SubjectType.PAIRWISE,c.getSubjectType());
        assertEquals("https://other.example.net/file_of_redirect_uris.json",c.getSectorIdentifierUri());
        assertEquals(ImmutableSet.of("https://client.example.org/callback","https://client.example.org/callback2"),c.getRegisteredRedirectUri());
        assertEquals("https://client.example.org/my_public_keys.jwks",c.getJwksUri());
        assertEquals(JWEAlgorithm.RSA1_5,c.getUserInfoEncryptedResponseAlg());
        assertEquals(EncryptionMethod.A128CBC_HS256,c.getUserInfoEncryptedResponseEnc());
        assertEquals(TokenEndPointAuthMethod.CLIENT_SECRET_BASIC,c.getTokenEndPointAuthMethod());
        assertEquals(ImmutableSet.of("https://client.example.org/rf.txt#qpXaRLh_n93TTR9F252ValdatUQvQiJi5BDub2BeznA"),c.getRequestUris());
        assertEquals(AppType.WEB,c.getApplicationType());
    }
}
