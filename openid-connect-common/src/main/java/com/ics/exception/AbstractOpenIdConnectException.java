package com.ics.exception;

import org.springframework.http.HttpStatus;

public abstract class AbstractOpenIdConnectException extends Exception{

    private String error;
    private String errorDescription;
    private String errorUri;
    private HttpStatus status;


    protected AbstractOpenIdConnectException(String error, String errorDescription){
        this.error = error;
        this.errorDescription = errorDescription;
    }

    protected AbstractOpenIdConnectException(String error, String errorDescription, String errorUri){
        this(error,errorDescription);
        this.errorUri = errorUri;
    }


    protected AbstractOpenIdConnectException(String error, String errorDescription, HttpStatus status){
        this(error, errorDescription);
        this.status = status;
    }

    protected AbstractOpenIdConnectException(String error, String errorDescription, String errorUri, HttpStatus status){
        this(error, errorDescription, errorUri);
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public String getErrorUri() {
        return errorUri;
    }

    public void setErrorUri(String errorUri) {
        this.errorUri = errorUri;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
