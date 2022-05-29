package com.ics.validator;

import com.ics.common.specs.oauth2.TokenEndPointAuthMethod;
import com.ics.validator.annotation.ClientAuthMethodValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ClientAuthMethodValidator implements ConstraintValidator<ClientAuthMethodValidation, TokenEndPointAuthMethod> {



    @Override
    public boolean isValid(TokenEndPointAuthMethod value, ConstraintValidatorContext context) {

        if (value == TokenEndPointAuthMethod.CLIENT_SECRET_BASIC || value == TokenEndPointAuthMethod.CLIENT_SECRET_JWT || value == TokenEndPointAuthMethod.CLIENT_SECRET_POST){
            return true;
        }

        if (value == TokenEndPointAuthMethod.PRIVATE_KEY_JWT){
            return true;
        }

        if (value == TokenEndPointAuthMethod.NONE){
            return true;
        }

        return false;
    }


}
