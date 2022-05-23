package com.ics.oauth2.models;

import com.google.gson.JsonObject;

import java.util.Date;

public class RegisteredClientDetails  {

    private String registeredClientAccessToken;
    private String registeredClientUri;
    private Date clientSecretExpireAt;
    private Date clientIdIssueAt;
    private ClientDetailsEntity client;
    private JsonObject jsonObject;

    public RegisteredClientDetails(){
        this.client = new ClientDetailsEntity();
    }

    public RegisteredClientDetails(ClientDetailsEntity client){
        this();
        this.client = client;
    }

    public RegisteredClientDetails(ClientDetailsEntity client, String registeredClientAccessToken, String registeredClientUri){
        this(client);
        this.registeredClientAccessToken = registeredClientAccessToken;
        this.registeredClientUri = registeredClientUri;
    }

    public String getRegisteredClientAccessToken() {
        return registeredClientAccessToken;
    }

    public void setRegisteredClientAccessToken(String registeredClientAccessToken) {
        this.registeredClientAccessToken = registeredClientAccessToken;
    }

    public String getRegisteredClientUri() {
        return registeredClientUri;
    }

    public void setRegisteredClientUri(String registeredClientUri) {
        this.registeredClientUri = registeredClientUri;
    }

    public Date getClientSecretExpireAt() {
        return clientSecretExpireAt;
    }

    public void setClientSecretExpireAt(Date clientSecretExpireAt) {
        this.clientSecretExpireAt = clientSecretExpireAt;
    }

    public Date getClientIdIssueAt() {
        return clientIdIssueAt;
    }

    public void setClientIdIssueAt(Date clientIdIssueAt) {
        this.clientIdIssueAt = clientIdIssueAt;
    }

    public ClientDetailsEntity getClient() {
        return client;
    }

    public void setClient(ClientDetailsEntity client) {
        this.client = client;
    }

    public JsonObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
    }


}
