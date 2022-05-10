//package com.ics.auth.server.openid.connect.models;
//
//import com.ics.common.Gender;
//import com.ics.openid.connect.models.BaseUserInfo;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//class BaseUserInfoTest {
//
//    @Test
//    void testUserInfo(){
//        BaseUserInfo u = new BaseUserInfo();
//
//        u.setName("xyz");
//        u.setEmail("xyz@gmail.com");
//        u.setGender(Gender.MALE.getValue());
//        u.setGivenName("xyz");
//
//
//        Assertions.assertEquals(Gender.MALE,Gender.getByValue(u.getGender()));
//        Assertions.assertEquals("xyz@gmail.com",u.getEmail());
//        Assertions.assertEquals("xyz",u.getName());
//    }
//}
