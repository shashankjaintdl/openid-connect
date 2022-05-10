package com.ics.openid.connect.models.converter;

import com.google.common.base.Strings;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
@SuppressWarnings("deprecation")
public class JsonObjectStringConverter implements AttributeConverter<JsonObject, String> {

    private JsonParser parser = new JsonParser();

    @Override
    public String convertToDatabaseColumn(JsonObject attribute) {
        if(attribute!=null){
            return attribute.toString();
        }
        return null;
    }

    @Override
    public JsonObject convertToEntityAttribute(String dbData) {
        if(!Strings.isNullOrEmpty(dbData)){
            return parser.parse(dbData).getAsJsonObject();
        }
        return null;
    }

}
