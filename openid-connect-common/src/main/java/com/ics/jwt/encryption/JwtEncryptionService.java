package com.ics.jwt.encryption;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.jwk.JWK;

import java.util.Collection;
import java.util.Map;

public interface JwtEncryptionService {

    JWEAlgorithm getEncryptorAlgorithm();

    Collection<JWEAlgorithm> getAllEncryptorAlgorithm();

    EncryptionMethod getEncryptedSupportedMethod();

    Collection<EncryptionMethod> getAllEncryptedSupportedMethod();

    void encryptJwt(JWEObject jwt);

    void decryptJwt(JWEObject jwt);

    Map<String, JWK> getAllPublicKey();

}
