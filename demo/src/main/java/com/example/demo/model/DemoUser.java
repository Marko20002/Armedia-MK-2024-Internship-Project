package com.example.demo.model;

import com.example.demo.model.enumerations.Role;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "demo_users")
public class DemoUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName,password;
    private boolean active;

    private String role;
    @JsonBackReference
    @OneToOne(cascade = CascadeType.ALL)
    Person person;

}
