package com.ics.certificate.service;

import com.ics.certificate.X509CertificateProperty;

import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public interface X509CertificateGeneratorService {

    X509Certificate generateX509Certificate(X509CertificateProperty property) throws NoSuchAlgorithmException, CertificateException, SignatureException, InvalidKeyException, KeyStoreException, IOException;

    void saveX509Certificate(X509Certificate certificate, PrivateKey privateKey) throws KeyStoreException, CertificateException, IOException, NoSuchAlgorithmException;


}
