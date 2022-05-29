package com.ics.jwt.signer.service.impl;

import com.google.common.base.Strings;
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

import java.util.*;

@Service
public class DefaultJwtSingerAndValidatorServiceImpl implements JwtSignService, JwtValidationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultJwtSingerAndValidatorServiceImpl.class);

    private final Map<String, JWSSigner> signers = new HashMap<>();

    private final Map<String, JWSVerifier> verifiers = new HashMap<>();

    private Map<String, JWK> jwks = new HashMap<>();

    private JWSAlgorithm defaultSigningAlg;

    private String defaultSignerKeyId;

    @Override
    public String getDefaultSignerKeyId() {
        return defaultSignerKeyId;
    }

    public void setDefaultSignerKeyId(String defaultSignerKeyId){
        this.defaultSignerKeyId = defaultSignerKeyId;
    }

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
        Collection<JWSAlgorithm> alg = new HashSet<>();
        for(JWSVerifier verifier:verifiers.values()){
            alg.addAll(verifier.supportedJWSAlgorithms());
        }
        for(JWSSigner signer: signers.values()){
            alg.addAll(signer.supportedJWSAlgorithms());
        }
        return alg;
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

        if (Strings.isNullOrEmpty(getDefaultSignerKeyId())){
            throw new IllegalStateException("");
        }

        JWSSigner jwsSigner = signers.get(getDefaultSignerKeyId());

        try {
            signedJWT.sign(jwsSigner);
        } catch (JOSEException e) {
            LOGGER.error("");
        }

    }

    @Override
    public void signJwt(SignedJWT signedJWT, JWSAlgorithm jwsAlgorithm) {
        JWSSigner jwsSigner = null;

        for (JWSSigner signer:signers.values()){
            if (signer.supportedJWSAlgorithms().contains(jwsAlgorithm)){
                jwsSigner = signer;
                break;
            }
        }

        if (jwsSigner==null){
            throw new IllegalStateException("");
        }

        try {
            signedJWT.sign(jwsSigner);
        }
        catch (JOSEException e) {
            LOGGER.error("");
        }

    }


}
