package com.example.demo.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "demo_person")
public class Person {
    @Id
    @GeneratedValue
    @Column(name = "demo_person_id")
    private long id;
    @Column(name = "demo_given_name")
    private String givenName;
    @Column(name = "demo_family_name")
    private String familyName;
    @Column(name = "demo_date_of_birth")
    private LocalDate dateOfBirth;
    @Column(name = "demo_place_of_birth")
    private String placeOfBirth;

    @JoinColumn(name = "person_demo_person_id")
    @OneToMany(cascade = CascadeType.ALL)
    private List<PostalAddress> addresses;
    @JoinColumn(name = "person_demo_person_id")
    @OneToMany(cascade = CascadeType.ALL)
    @Valid
    private List<ContactMethod> contactMethods;


}
