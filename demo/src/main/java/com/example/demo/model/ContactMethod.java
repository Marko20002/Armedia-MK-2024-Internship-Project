package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.lang.annotation.Target;

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
