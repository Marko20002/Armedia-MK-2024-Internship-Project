package model;

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

    public String getGivenName() {
        return givenName;
    }

    public String getFamiluName() {
        return familuName;
    }

    public Date getDateofBirth() {
        return dateofBirth;
    }

    public String getPlaceofBirth() {
        return placeofBirth;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
