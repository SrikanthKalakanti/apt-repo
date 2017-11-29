package com.apt.msa.util;

import java.lang.annotation.Documented;

import javax.validation.Constraint;

@Constraint(validatedBy = EmailValidator.class)
@Documented
public @interface ValidEmail {   
    String message() default "Invalid email";
}