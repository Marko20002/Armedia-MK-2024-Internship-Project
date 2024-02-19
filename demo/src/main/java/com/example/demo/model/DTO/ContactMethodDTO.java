package com.example.demo.model.DTO;

import com.example.demo.model.enumerations.ContactMethodType;

import com.example.demo.model.ValidContactMethod;
import lombok.Builder;
import lombok.Data;


import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@Builder
@ValidContactMethod
public class ContactMethodDTO {
    @NotNull
    private ContactMethodType type;

    @Valid
    @NotBlank(message = "Invalid Phone/Email value: Empty value", groups = {EmailValidation.class, PhoneValidation.class})
    private String value;

    @NotNull
    private String description;

    public interface EmailValidation {}

    public interface PhoneValidation {}



}
