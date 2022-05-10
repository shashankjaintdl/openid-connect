package com.ics.oauth2.models.convert;

import com.nimbusds.jose.EncryptionMethod;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class JWEEncryptedMethodStringConverter implements AttributeConverter<EncryptionMethod,String> {

    @Override
    public String convertToDatabaseColumn(EncryptionMethod attribute) {
        if(attribute!=null){
            return attribute.getName();
        }
        return null;
    }

    @Override
    public EncryptionMethod convertToEntityAttribute(String dbData) {
        if(dbData!=null){
            return EncryptionMethod.parse(dbData);
        }
        return null;
    }

}
