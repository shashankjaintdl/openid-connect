package com.ics.oauth2.endpoint;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping(TokenEndpoint.ENDPOINT)
public class TokenEndpoint {

    public static final String ENDPOINT =  "oauth2/token";

    @PostMapping
    public ResponseEntity<String> createToken(@RequestParam Map<String, String> parameter, Principal principal){


        return null;
    }

}
