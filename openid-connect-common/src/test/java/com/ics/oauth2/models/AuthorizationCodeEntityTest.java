package com.ics.oauth2.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class AuthorizationCodeEntityTest {

    @Test
    void testAuthorizationCodeEntity(){
        AuthorizationCodeEntity authorizationCode = new AuthorizationCodeEntity();
        authorizationCode.setCode("1234");

        Assertions.assertEquals("1234",authorizationCode.getCode());

    }
}
