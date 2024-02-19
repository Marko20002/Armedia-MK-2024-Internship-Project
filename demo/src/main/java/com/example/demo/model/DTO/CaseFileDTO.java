package com.example.demo.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CaseFileDTO {

    private String caseNumber;
    private String title;
    private Date incidentDate;
    private PersonDTO personDTO;
    private Long personId;
    private Long id;
}