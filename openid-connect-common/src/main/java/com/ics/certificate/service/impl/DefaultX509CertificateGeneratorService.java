package com.ics.certificate.service.impl;

import com.ics.certificate.X509CertificateProperty;
import com.ics.certificate.service.X509CertificateGeneratorService;
import org.bouncycastle.jce.X509Principal;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.x509.X509V3CertificateGenerator;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;

@Service
public class DefaultX509CertificateGeneratorService implements X509CertificateGeneratorService {

    private static final String CERTIFICATE_ALIAS = "YOUR_CERTIFICATE_NAME";
    private static final String CERTIFICATE_ALGORITHM = "RSA";
    private static final String CERTIFICATE_DN = "CN=cn, O=o, L=L, ST=il, C= c";
    private static final String CERTIFICATE_NAME = "keystore.test";
    private static final int CERTIFICATE_BITS = 1024;

    static {
        Security.addProvider(new BouncyCastleProvider());
    }


    @Override
    @SuppressWarnings("deprecation")
    public X509Certificate generateX509Certificate(X509CertificateProperty property) throws NoSuchAlgorithmException, CertificateException, SignatureException, InvalidKeyException, KeyStoreException, IOException {
        X509Certificate certificate;

        KeyPairGenerator keyPairGenerator =  KeyPairGenerator.getInstance(CERTIFICATE_ALGORITHM);
        keyPairGenerator.initialize(2048,new SecureRandom());
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        X509V3CertificateGenerator v3CertGen =  new X509V3CertificateGenerator();
        v3CertGen.setSerialNumber(BigInteger.valueOf(System.currentTimeMillis()));
        v3CertGen.setIssuerDN(new X509Principal(property.getX509Principal()));
        v3CertGen.setNotBefore(new Date(System.currentTimeMillis() - 1000L * 60 * 60 * 24));
        v3CertGen.setNotAfter(new Date(System.currentTimeMillis() + (1000L * 60 * 60 * 24 * 365*10)));
        v3CertGen.setSubjectDN(new X509Principal(CERTIFICATE_DN));
        v3CertGen.setPublicKey(keyPair.getPublic());
        v3CertGen.setSignatureAlgorithm("SHA256WithRSAEncryption");

        certificate = v3CertGen.generate(keyPair.getPrivate());

        saveX509Certificate(certificate, keyPair.getPrivate());

        return certificate;
    }

    @Override
    public void saveX509Certificate(X509Certificate certificate, PrivateKey privateKey) throws KeyStoreException, CertificateException, IOException, NoSuchAlgorithmException {

        KeyStore store = KeyStore.getInstance("JKS");
        store.load(null,null);
        store.setKeyEntry("alias",privateKey,"password".toCharArray(),new java.security.cert.Certificate[]{certificate});
        File file = new File("","keystore.jks");
        store.store(new FileOutputStream(file),"password".toCharArray());
    }


}
