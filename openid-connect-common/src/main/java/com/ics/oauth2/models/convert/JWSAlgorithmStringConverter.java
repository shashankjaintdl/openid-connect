package com.ics.oauth2.models.convert;

import com.nimbusds.jose.JWSAlgorithm;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class JWSAlgorithmStringConverter implements AttributeConverter<JWSAlgorithm, String> {

    @Override
    public String convertToDatabaseColumn(JWSAlgorithm attribute) {
        if(attribute!=null){
            return attribute.getName();
        }
        return null;
    }

    @Override
    public JWSAlgorithm convertToEntityAttribute(String dbData) {
        if(dbData!=null){
            return JWSAlgorithm.parse(dbData);
        }
        return null;
    }

}
