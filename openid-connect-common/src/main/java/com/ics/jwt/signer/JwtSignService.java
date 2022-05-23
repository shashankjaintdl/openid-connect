package com.ics.jwt.signer;


import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jwt.SignedJWT;

import java.util.Collection;

public interface JwtSignService {

//JWEEncrypter;
//JWEDecrypter;
//JWEAlgorithm;
//JWSAlgorithm;
//JWSSigner;
//JWSVerifier;

    JWSAlgorithm getSigningAlgorithm();

    Collection<JWSAlgorithm> getAllSigningAlgorithm();

    void signJwt(SignedJWT signedJWT);

    void signJwt(SignedJWT signedJWT, JWSAlgorithm jwsAlgorithm);

}
