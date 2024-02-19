package com.example.demo.model;

import com.example.demo.model.DTO.ContactMethodDTO;
import com.example.demo.model.enumerations.ContactMethodType;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class ContactMethodValidator implements ConstraintValidator<ValidContactMethod, ContactMethodDTO> {

    @Value("${emailRegex}")
    private String emailR;
    @Value("${phoneRegex}")
    private String phoneR;


    @Override
    public void initialize(ValidContactMethod constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(ContactMethodDTO contactMethod, ConstraintValidatorContext constraintValidatorContext) {
        String value = contactMethod.getValue();
        if (contactMethod.getType().equals(ContactMethodType.EMAIL)) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("Invalid email address")
                    .addConstraintViolation();
            return value.matches(emailR);
        } else if (contactMethod.getType().equals(ContactMethodType.PHONE)) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("Invalid phone number")
                    .addConstraintViolation();
            return value.matches(phoneR);
        } else {
            return false;
        }
    }
}
