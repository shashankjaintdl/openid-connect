package com.ics.oauth2.utils;

import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.JWKSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.*;

public class JsonUtils {

    public static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

    private static final Gson GSON = new Gson();

    private JsonUtils(){}

    /**
     * Gets the value of the given field as a String, null if it doesn't exist
     */

    public static String getAsString(JsonObject o, String field){
        if(o.has(field)){
            JsonElement jsonElement = o.get(field);
            if(jsonElement!=null && jsonElement.isJsonPrimitive()) {
                return jsonElement.getAsString();
            }
            else {
                return null;
            }
        }
        return null;
    }

    /**
     * Gets the value of the given field as a Long, null if it doesn't exist
     */

    public static Long getAsLong(JsonObject o, String field){
        if(o.has(field)){
            JsonElement jsonElement = o.get(field);
            if(jsonElement!=null && jsonElement.isJsonPrimitive()){
                return jsonElement.getAsLong();
            }
            else {
                return null;
            }
        }
        return null;
    }

    /**
     * Gets the value of the given field as Integer, null if it doesn't exist
     */

    public static Integer getAsInteger(JsonObject o, String field){
        if(o.has(field)){
            JsonElement jsonElement = o.get(field);
            if(jsonElement!=null && jsonElement.isJsonPrimitive()){
                return jsonElement.getAsInt();
            }
            else {
                return null;
            }
        }
        return null;
    }

    /**
     * Gets the value of the given field as a Boolean, null if it doesn't exist
     */

    public static Boolean getAsBoolean(JsonObject o, String field){
        if(o.has(field)){
            JsonElement jsonElement = o.get(field);
            if(jsonElement!=null && jsonElement.isJsonPrimitive()){
                return jsonElement.getAsBoolean();
            }
            else {
                return false;
            }
        }
        return false;
    }

    /**
     * Gets the value of the given field as a Date, null if it doesn't exist
     */

    public static Date getAsDate(JsonObject o, String field){
        if(o.has(field)){
            JsonElement jsonElement = o.get(field);
            if(jsonElement!=null && jsonElement.isJsonPrimitive()){
                return new Date(jsonElement.getAsInt() *  1000L);
            }
            else{
                return null;
            }
        }
        return null;
    }

    /**
     * Gets the value of the given field as a set of String, emptySet if it doesn't exist
     */

    public static Set<String> getAsStringSet(JsonObject o, String field) throws JsonSyntaxException{

        if(o.has(field)){
            if(o.get(field).isJsonArray()){
                return GSON.fromJson(o.get(field), new TypeToken<Set<String>>(){}.getType());
            }
            else{
                Sets.newHashSet(o.get(field).getAsString());
            }
        }
        return Collections.emptySet();
    }


    /**
     * Gets the value of the given field as a list of String, emptyList if it doesn't exist
     */

    public static List<String> getAsStringList(JsonObject o, String field) throws JsonSyntaxException {

        if(o.has(field)){
            if(o.get(field).isJsonArray()){
                return GSON.fromJson(o.get(field), new TypeToken<List<String>>(){}.getType());
            }
            else{
                Sets.newHashSet(o.get(field).getAsString());
            }
        }
        return Collections.emptyList();
    }


    /**
     * Gets the value of the given field as a JWSAlgorithm, null if it doesn't exist
     */

    public static JWSAlgorithm getAsJwsAlgorithm(JsonObject o, String field) {
        String s = getAsString(o, field);
        if (s != null) {
            return JWSAlgorithm.parse(s);
        } else {
            return null;
        }
    }

    /**
     * Gets the value of the given field as a JWEAlgorithm, null if it doesn't exist
     */

    public static JWEAlgorithm getAsJweAlgorithm(JsonObject o, String field) {
        String s = getAsString(o, field);
        if (s != null) {
            return JWEAlgorithm.parse(s);
        } else {
            return null;
        }
    }

    /**
     * Gets the value of the given field as a EncryptionMethod, null if it doesn't exist
     */
    
    public static EncryptionMethod getEncryptedMethod(JsonObject o, String field){
        String s = getAsString(o, field);
        if(s!=null){
            return EncryptionMethod.parse(s);
        }
        else{
            return null;
        }
    }

    /**
     * Gets the value of the given field as a JWKSet, null if it doesn't exist
     */

    public static JWKSet getAsJWKSet(JsonObject o, String field){
        if(o.has(field)){
            JsonElement jsonElement = o.get(field);
            if(jsonElement!=null && jsonElement.isJsonObject()){
                try {
                    return JWKSet.parse(jsonElement.toString());
                } catch (ParseException e) {
                    LOGGER.error("Unable to parse JWKSet", e);
                    return null;
                }
            }
        }
        return null;
    }

    public static List<JWSAlgorithm> getAsJwsAlgorithmList(JsonObject o, String field) {
        List<String> strings = getAsStringList(o,field);
        if(strings!=null){
            List<JWSAlgorithm> algorithmList = new ArrayList<>();
            for (String s:strings){
                algorithmList.add(JWSAlgorithm.parse(s));
            }
            return algorithmList;
        }
        return null;
    }


    public static List<JWEAlgorithm> getAsJweAlgorithmList(JsonObject o, String field) {
        List<String> strings = getAsStringList(o,field);
        if(strings!=null){
            List<JWEAlgorithm> algorithmList = new ArrayList<>();
            for (String s:strings){
                algorithmList.add(JWEAlgorithm.parse(s));
            }
            return algorithmList;
        }
        return null;
    }

    public static List<EncryptionMethod> getAsEncryptionMethodList(JsonObject o, String field) {
        List<String> strings = getAsStringList(o,field);
        if(strings!=null){
            List<EncryptionMethod> algorithmList = new ArrayList<>();
            for (String s:strings){
                algorithmList.add(EncryptionMethod.parse(s));
            }
            return algorithmList;
        }
        return null;
    }

}
