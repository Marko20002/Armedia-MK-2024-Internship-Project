package com.example.demo.model.DTO;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class PostalAddressDTO {

    @NotBlank(message = "Invalid streetAddress: Empty streetAddress")
    //@NotNull(message = "Invalid streetAddress: StreetAddress is Null")
    private String streetAddress;

    @NotBlank(message = "Invalid city: Empty city")
    //@NotNull(message = "Invalid city: City is Null")
    private String city;

    @NotBlank(message = "Invalid Zip: Empty Date Of Birth")
    //@NotNull(message = "Invalid zip: Zip is Null")
    private String zip;

    @NotBlank(message = "Invalid country: Empty country")
   // @NotNull(message = "Invalid country: Country is Null")
    private String country;

}
