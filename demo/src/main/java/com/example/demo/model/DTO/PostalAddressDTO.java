package com.example.demo.model.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@Builder
public class PostalAddressDTO {

    @NotBlank(message = "Invalid streetAddress: Empty streetAddress")
    private String streetAddress;

    @NotBlank(message = "Invalid city: Empty city")
    private String city;

    @NotBlank(message = "Invalid Zip: Empty Date Of Birth")
    private String zip;

    @NotBlank(message = "Invalid country: Empty country")
    private String country;

}
