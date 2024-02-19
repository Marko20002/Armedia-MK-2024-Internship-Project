package com.example.demo.model;

import com.example.demo.model.Person;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "p_case_file")
@NamedQueries({
        @NamedQuery(name = "CaseFile.findAll", query = "SELECT cf FROM CaseFile cf"),
        @NamedQuery(name = "CaseFile.findById", query = "SELECT cf FROM CaseFile cf WHERE cf.id= :id"),
        @NamedQuery(name = "CaseFile.updateFile", query = "UPDATE CaseFile c " +
                "SET c.caseNumber = :caseNumber, " + "c.title = :title, " + "c.incidentDate = :incidentDate " + "WHERE c.id = :id")
})
public class CaseFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "case_file_id")
    private Long id;

    @Column(name = "p_case_number")
    private Integer caseNumber;

    @Column(name = "p_title")
    private String title;

    @Column(name = "p_incident_date")
    private Date incidentDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id")
    private Person person;
}