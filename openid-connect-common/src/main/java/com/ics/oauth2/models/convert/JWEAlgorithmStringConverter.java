package com.ics.oauth2.models.convert;

import com.nimbusds.jose.JWEAlgorithm;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class JWEAlgorithmStringConverter implements AttributeConverter<JWEAlgorithm, String> {

    @Override
    public String convertToDatabaseColumn(JWEAlgorithm attribute) {
        if(attribute!=null){
            return attribute.getName();
        }
        return null;
    }

    @Override
    public JWEAlgorithm convertToEntityAttribute(String dbData) {
        if(dbData!=null){
            return JWEAlgorithm.parse(dbData);
        }
        return null;
    }

}
