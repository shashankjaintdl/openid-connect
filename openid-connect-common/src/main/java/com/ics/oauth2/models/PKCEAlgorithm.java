package com.ics.oauth2.models;

import com.nimbusds.jose.Algorithm;
import com.nimbusds.jose.Requirement;

public final class PKCEAlgorithm extends Algorithm {

    public static final PKCEAlgorithm PLAIN = new PKCEAlgorithm("plain",Requirement.REQUIRED);
    public static final PKCEAlgorithm SHA_256 = new PKCEAlgorithm("S256",Requirement.OPTIONAL);


    public PKCEAlgorithm(String name) {
        super(name,null);
    }

    public PKCEAlgorithm(String name, Requirement req) {
        super(name, req);
    }

    public static PKCEAlgorithm parse(final String s){
        if(s.equals(PLAIN.getName())){
            return PLAIN;
        }
        else if(s.equals(SHA_256.getName())) {
            return SHA_256;
        }
        else {
            return new PKCEAlgorithm(s);
        }
    }


}
