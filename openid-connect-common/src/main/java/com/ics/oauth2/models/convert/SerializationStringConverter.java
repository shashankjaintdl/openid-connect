package com.ics.oauth2.models.convert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.Serializable;
import java.util.Date;

@Converter
public class SerializationStringConverter implements AttributeConverter<Serializable, String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SerializationStringConverter.class);

    @Override
    public String convertToDatabaseColumn(Serializable attribute) {
        if (attribute==null){
            return null;
        }
        else if(attribute instanceof Integer){
            return String.valueOf(attribute);
        }
        else if(attribute instanceof Long){
            return String.valueOf(attribute);
        }
        else if(attribute instanceof Date){
            return Long.toString(((Date)attribute).getTime());
        }
        else{
            LOGGER.warn("Dropping the data from request {} :: {}",attribute, attribute.getClass());
            return null;
        }
    }

    @Override
    public Serializable convertToEntityAttribute(String dbData) {
        return dbData;
    }
}
