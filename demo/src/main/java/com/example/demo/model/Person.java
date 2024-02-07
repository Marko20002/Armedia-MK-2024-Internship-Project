package com.example.demo.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@NamedQueries({
        @NamedQuery(name = Person.PERSON_FIND_ALL,
                query = "SELECT p FROM Person p"),
        @NamedQuery(name = Person.PERSON_FIND_BY_ID,
                query = "SELECT p FROM Person p WHERE p.id = :id"),
        @NamedQuery(name = Person.PERSON_FIND_BY_EMAIL,
                query = "SELECT p FROM Person p JOIN p.contactMethods" +
                        " c WHERE c.type='EMAIL' AND c.value= :email"),
        @NamedQuery(name = Person.PERSON_FIND_BY_STREET_ADDRESS,
                query = "SELECT p FROM Person p JOIN p.addresses " +
                        "pa WHERE pa.streetAddress= :streetAddress"),
        @NamedQuery(name = Person.PERSON_FIND_BY_ID_ADD_ADDRESS,
                query = "SELECT p FROM Person p LEFT JOIN " +
                        "p.addresses pa WHERE p.id= :id"),
        @NamedQuery(name = Person.PERSON_FIND_BY_ID_ADD_CONTACT,
                query = "SELECT p FROM Person p LEFT JOIN " +
                        "p.contactMethods c WHERE p.id= :id")
})
@Entity
@Table(name = "demo_person")
public class Person {
    public static final String PERSON_FIND_BY_EMAIL = "person.findByEmail";
    public static final String PERSON_FIND_ALL = "Person.findAll";
    public static final String PERSON_FIND_BY_ID = "Person.findById";
    public static final String PERSON_FIND_BY_STREET_ADDRESS = "Person.findByStreetAddress";
    public static final String PERSON_FIND_BY_ID_ADD_ADDRESS = "Person.findByIdAddAddress";
    public static final String PERSON_FIND_BY_ID_ADD_CONTACT = "Person.findByIdAddContact";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "demo_person_id")
    private long id;
    @Column(name = "demo_given_name")
    private String Name;
    @Column(name = "demo_family_name")
    private String familyName;
    @Column(name = "demo_date_of_birth")
    private LocalDate dateOfBirth;
    @Column(name = "demo_place_of_birth")
    private String placeOfBirth;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_demo_person_id")
    private List<PostalAddress> addresses;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_demo_person_id")
    @Valid
    private List<ContactMethod> contactMethods;

}
