package com.ics.exception;

import org.springframework.http.HttpStatus;

public class ValidationException extends AbstractOpenIdConnectException{

    public ValidationException(String error, String errorDescription) {
        super(error, errorDescription);
    }

    public ValidationException(String error, String errorDescription, String errorUri) {
        super(error, errorDescription, errorUri);
    }

    public ValidationException(String error, String errorDescription, HttpStatus status) {
        super(error, errorDescription, status);
    }

    public ValidationException(String error, String errorDescription, String errorUri, HttpStatus status) {
        super(error, errorDescription, errorUri, status);
    }
}
