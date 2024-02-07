package com.example.demo.model.DTO;

import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class PersonDTO {

    @NotBlank(message = "Invalid name: Empty name")
    @NotNull(message = "Invalid name: Name is Null")
    String name;

    @NotBlank(message = "Invalid surname: Empty surname")
    @NotNull(message = "Invalid surname: Surname is Null")
    String familyName;

//  @NotBlank(message = "Invalid date Of Birth: Empty Date Of Birth")
//   @NotNull(message = "Invalid date Of Birth: Name is Date Of Birth")
    LocalDate dateOfBirth;

    @NotBlank(message = "Invalid place Of Birth: Empty Place Of Birth")
    @NotNull(message = "Invalid place Of Birth: Name is Place Of Birth")
    String placeOfBirth;

    @Valid
    List<ContactMethodDTO> contactMethods;
    @Valid
    List<PostalAddressDTO> addresses;


}
