package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Setter
@Getter
@Entity
@Table(name = "demo_postal_address")
public class PostalAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "demo_postal_address_id")
    private Long Id;
    @Column(name = "demo_street_address")//, unique = true)
    private String streetAddress;
    @Column(name = "demo_city")
    private String city;
    @Column(name = "demo_zip")
    private String zip;
    @Column(name = "demo_country")
    private String country;

    @ManyToOne
    @JsonBackReference
    public Person person;


}

