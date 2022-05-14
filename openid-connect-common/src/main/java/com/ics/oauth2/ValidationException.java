package com.ics.oauth2;

import org.springframework.http.HttpStatus;

public class ValidationException extends Exception{

    private String error;
    private String errorDescription;
    private HttpStatus status;

    public ValidationException(String error, String errorDescription,
                               HttpStatus status) {
        this.error = error;
        this.errorDescription = errorDescription;
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

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
