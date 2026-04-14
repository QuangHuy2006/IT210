package org.example.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.model.TourDTO;

public class DateValidator implements ConstraintValidator<ValidTourDate, TourDTO> {

    @Override
    public boolean isValid(TourDTO dto, ConstraintValidatorContext context) {
        if (dto == null) return true;


        if (dto.getStartDate() == null || dto.getEndDate() == null) return true;

        boolean valid = dto.getEndDate().isAfter(dto.getStartDate());

        if (!valid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("End date must be after start date")
                    .addPropertyNode("endDate")
                    .addConstraintViolation();
        }

        return valid;
    }
}