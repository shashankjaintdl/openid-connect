package com.ics.jwt.signer;

import com.nimbusds.jwt.SignedJWT;

public interface JwtValidationService {

    boolean validateSign(SignedJWT signedJWT);


}
