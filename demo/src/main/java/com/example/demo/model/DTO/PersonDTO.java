package com.example.demo.model.DTO;

import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class PersonDTO {

    @NotBlank(message = "Invalid name: Empty name")
    String name;
    @NotBlank(message = "Invalid surname: Empty surname")
    String familyName;
    //  @NotBlank(message = "Invalid date Of Birth: Empty Date Of Birth")
    LocalDate dateOfBirth;
    @NotBlank(message = "Invalid place Of Birth: Empty Place Of Birth")
    String placeOfBirth;
    @Valid
    List<ContactMethodDTO> contactMethods;
    @Valid
    List<PostalAddressDTO> addresses;
    UserDTO user;
}
