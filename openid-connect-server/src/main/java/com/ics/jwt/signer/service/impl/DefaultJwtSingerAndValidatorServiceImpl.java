package com.ics.jwt.signer.service.impl;

import com.ics.jwt.signer.JwtSignService;
import com.ics.jwt.signer.JwtValidationService;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jwt.SignedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class DefaultJwtSingerAndValidatorServiceImpl implements JwtSignService, JwtValidationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultJwtSingerAndValidatorServiceImpl.class);

    private Map<String, JWSSigner> signers = new HashMap<>();

    private Map<String, JWSVerifier> verifiers = new HashMap<>();

    private Map<String, JWK> jwks = new HashMap<>();

    private JWSAlgorithm defaultSigningAlg;

    @Override
    public JWSAlgorithm getSigningAlgorithm() {
        return defaultSigningAlg;
    }

    public void setDefaultSigningAlgName(String algName){
        this.defaultSigningAlg = JWSAlgorithm.parse(algName);
    }

    public String getDefaultSigningAlgName(){
        if (defaultSigningAlg!=null){
            return defaultSigningAlg.getName();
        }
        return null;
    }

    @Override
    public Collection<JWSAlgorithm> getAllSigningAlgorithm() {

        return null;
    }


    @Override
    public boolean validateSign(SignedJWT signedJWT) {
        for (JWSVerifier verifier: verifiers.values()) {
            try{
                return signedJWT.verify(verifier);
            }
            catch (JOSEException ex){
                LOGGER.error(String.format("Failed to validate the signature with %s error message %s", verifier, ex.getMessage()));
            }
        }
        return false;
    }

    @Override
    public void signJwt(SignedJWT signedJWT) {

    }

    @Override
    public void signJwt(SignedJWT signedJWT, JWSAlgorithm jwsAlgorithm) {

    }


}
