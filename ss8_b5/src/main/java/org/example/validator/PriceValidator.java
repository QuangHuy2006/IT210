package org.example.validator;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.model.TourDTO;

public class PriceValidator implements ConstraintValidator<ValidPrice, TourDTO> {

    @Override
    public boolean isValid(TourDTO dto, ConstraintValidatorContext context) {
        if (dto == null) return true;

        boolean valid = dto.getChildPrice() <= dto.getAdultPrice();

        if (!valid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Child price <= Adult price")
                    .addPropertyNode("childPrice")
                    .addConstraintViolation();
        }

        return valid;
    }
}