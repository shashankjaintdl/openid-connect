package com.ics.oauth2.models.convert;

import com.nimbusds.jose.jwk.JWKSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.text.ParseException;

@Converter
public class JWKSetStringConverter implements AttributeConverter<JWKSet, String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(JWKSetStringConverter.class);

    @Override
    public String convertToDatabaseColumn(JWKSet attribute) {
        if(attribute!=null){
            return attribute.toString();
        }
        return null;
    }

    @Override
    public JWKSet convertToEntityAttribute(String dbData) {
        if(dbData!=null){
            try {
                return JWKSet.parse(dbData);
            } catch (ParseException e) {
                LOGGER.error("Unable to parse JWKSet ",e);
                return null;
            }
        }
        return null;
    }
}
