package com.ics.oauth2.models;

import com.ics.common.specs.openid.Scope;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ReservedScopeTest {

    @Test
    void testReservedScopeEntity(){
        ReservedScope reservedScope = new ReservedScope();

        reservedScope.setId(2L);
        reservedScope.setReserved(true);
        reservedScope.setScope(Scope.ADDRESS.getValue());
        reservedScope.setRestricted(false);
        reservedScope.setDescription("Address Scope");

        Assertions.assertEquals(2,reservedScope.getId());
        Assertions.assertEquals("address",reservedScope.getScope());
        Assertions.assertEquals("Address Scope",reservedScope.getDescription());
        Assertions.assertTrue(reservedScope.isReserved());
        Assertions.assertFalse(reservedScope.isRestricted());

    }
}
