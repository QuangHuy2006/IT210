package org.example.session08.validator;


import jakarta.validation.Payload;

public @interface PasswordMatch{
    String message() default "invalid password";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}