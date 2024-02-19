package com.example.demo.model;

import com.example.demo.model.enumerations.ContactMethodType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "demo_contact_method")
public class ContactMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "demo_contact_method")
    private ContactMethodType type;

    @Column(name = "demo_value",unique = true)
    private String value;

    @Column(name = "demo_description")
    private String description;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonBackReference
    private Person person;



}
