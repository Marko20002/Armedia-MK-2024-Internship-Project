package com.example.demo.model;

import org.springframework.beans.factory.annotation.Value;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class ContactMethodValidator implements ConstraintValidator<ValidContactMethod, ContactMethod> {

    @Value("${emailRegex}")
    private String emailR;
    @Value("${phoneRegex}")
    private String phoneR;


    @Override
    public void initialize(ValidContactMethod constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(ContactMethod contactMethod, ConstraintValidatorContext constraintValidatorContext) {
        String value = contactMethod.getValue();
        if (contactMethod.getType() == ContactMethodType.EMAIL) {
            return value.matches(emailR);
        } else if (contactMethod.getType() == ContactMethodType.PHONE) {
            return value.matches(phoneR);
        } else {
            return false;
        }
    }
}
