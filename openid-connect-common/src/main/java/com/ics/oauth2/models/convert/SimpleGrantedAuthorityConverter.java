package com.ics.oauth2.models.convert;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Converter
public class SimpleGrantedAuthorityConverter implements AttributeConverter<SimpleGrantedAuthority,String> {

    public static final Logger LOGGER = LoggerFactory.getLogger(SimpleGrantedAuthorityConverter.class);


    @Override
    public String convertToDatabaseColumn(SimpleGrantedAuthority attribute) {
        return null;
    }

    @Override
    public SimpleGrantedAuthority convertToEntityAttribute(String dbData) {
        return null;
    }


}
