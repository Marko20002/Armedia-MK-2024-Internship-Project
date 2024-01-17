package com.example.demo.model;

import javax.persistence.*;
import java.util.Date;


@Entity
public class Person {
    @Id
    @GeneratedValue
    private long id;
    private String givenName;
    private String familyName;
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    private String placeOfBirth;


    public Person(String givenName, String familyName, Date dateOfBirth, String placeOfBirth) {
        this.givenName = givenName;
        this.familyName = familyName;
        this.dateOfBirth = dateOfBirth;
        this.placeOfBirth = placeOfBirth;
    }

    public Person() {
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateofBirth) {
        this.dateOfBirth = dateofBirth;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeofBirth) {
        this.placeOfBirth = placeofBirth;
    }

}
