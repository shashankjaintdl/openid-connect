package com.ics;

import org.apache.commons.codec.binary.Base64;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigInteger;
import java.util.Random;

@SpringBootApplication
public class OpenIdConnectWebApplication  implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(OpenIdConnectWebApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String str = Base64.encodeBase64URLSafeString(new BigInteger(512, new Random()).toByteArray());
        System.out.println(str);
    }
}
