package org.example.session08.validator;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.session08.dto.RegisterDTO;

import java.util.Objects;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, RegisterDTO> {
    @Override
    public boolean isValid(RegisterDTO registerDTO, ConstraintValidatorContext constraintValidatorContext) {

        if(registerDTO == null) return true;


        boolean valid = Objects.equals(registerDTO.getPassword(), registerDTO.getConfirmPassword());

        if(!valid){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("Password không khớp")
                    .addPropertyNode("confirmPassword")
                    .addConstraintViolation();
        }
        return valid;
    }
}
