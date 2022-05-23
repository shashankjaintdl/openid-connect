package com.ics.oauth2.models.convert;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Converter
public class SimpleGrantedAuthorityConverter implements AttributeConverter<SimpleGrantedAuthority,String> {



    @Override
    public String convertToDatabaseColumn(SimpleGrantedAuthority attribute) {
        if(attribute!=null){
            return attribute.getAuthority();
        }
        return null;
    }

    @Override
    public SimpleGrantedAuthority convertToEntityAttribute(String dbData) {
        if(dbData==null){
            return null;
        }
        return new SimpleGrantedAuthority(dbData);
    }


}
