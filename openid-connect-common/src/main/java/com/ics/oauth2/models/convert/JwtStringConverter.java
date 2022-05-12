package com.ics.oauth2.models.convert;

import com.google.common.base.Strings;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.text.ParseException;

@Converter
public class JwtStringConverter implements AttributeConverter<JWT, String> {

    public static final Logger LOGGER = LoggerFactory.getLogger(JwtStringConverter.class);

    @Override
    public String convertToDatabaseColumn(JWT attribute) {
        if(attribute!=null){
            return attribute.serialize();
        }
        return null;
    }

    @Override
    public JWT convertToEntityAttribute(String dbData) {
        if(!Strings.isNullOrEmpty(dbData)){
            try{
                return JWTParser.parse(dbData);
            }
            catch (ParseException e) {
                LOGGER.error("Unable to parse JWT :: ", e);
            }
        }
        return null;
    }
}
