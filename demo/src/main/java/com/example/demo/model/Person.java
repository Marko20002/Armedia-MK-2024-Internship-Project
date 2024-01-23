package com.example.demo.model;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Entity
public class Person {
    @Id
    @GeneratedValue
    private long id;
    private String givenName;
    private String familyName;
    private LocalDate dateOfBirth;
    private String placeOfBirth;

    @OneToMany(cascade = CascadeType.ALL)
    private List<PostalAddress> addresses;
    @OneToMany(cascade = CascadeType.ALL)
    private List<ContactMethod> contactMethods;
    public List<ContactMethod> getContactMethods() {
        return contactMethods;
    }

    public void setContactMethods(List<ContactMethod> contactMethods) {
        this.contactMethods = contactMethods;
    }


    public List<PostalAddress> getAddresses() {
        return addresses;
    }
    public void setAddresses(List<PostalAddress> addresses) {
        this.addresses = addresses;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateofBirth) {
        this.dateOfBirth = dateofBirth;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeofBirth) {
        this.placeOfBirth = placeofBirth;
    }

}
