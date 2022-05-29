package com.ics.validator.annotation;

import com.ics.common.specs.oauth2.TokenEndPointAuthMethod;
import com.ics.validator.ClientAuthMethodValidator;

import javax.validation.Constraint;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

@Target( { FIELD, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ClientAuthMethodValidator.class)
public @interface ClientAuthMethodValidation {

    String message() default "{com.ics.validator.annotation.message}";

    TokenEndPointAuthMethod value() default TokenEndPointAuthMethod.CLIENT_SECRET_BASIC;

}
