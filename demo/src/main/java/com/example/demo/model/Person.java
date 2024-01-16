package com.example.demo.model;

import lombok.Setter;

import javax.persistence.*;
import java.util.Date;



@Entity
public class Person {
    @Id
    @GeneratedValue
    private long id;
    private String givenName;
    private String familuName;
    @Temporal(TemporalType.DATE)
    private Date dateofBirth;
    private String placeofBirth;

    public Person(long id, String givenName, String familuName, Date dateofBirth, String placeofBirth) {
        this.id = id;
        this.givenName = givenName;
        this.familuName = familuName;
        this.dateofBirth = dateofBirth;
        this.placeofBirth = placeofBirth;
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

    public String getFamiluName() {
        return familuName;
    }

    public void setFamiluName(String familuName) {
        this.familuName = familuName;
    }

    public Date getDateofBirth() {
        return dateofBirth;
    }

    public void setDateofBirth(Date dateofBirth) {
        this.dateofBirth = dateofBirth;
    }

    public String getPlaceofBirth() {
        return placeofBirth;
    }

    public void setPlaceofBirth(String placeofBirth) {
        this.placeofBirth = placeofBirth;
    }
}
