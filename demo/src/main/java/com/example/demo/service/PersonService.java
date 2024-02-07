package com.example.demo.service;

import com.example.demo.model.ContactMethod;
import com.example.demo.model.DTO.PersonDTO;
import com.example.demo.model.Person;
import com.example.demo.model.PostalAddress;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


public interface PersonService {

    @Transactional
    Optional<Person> createPerson(PersonDTO person);

    List<Person> listAll();

   Optional getbyId(Long Id);

   List<Person>findByEmail(String email);
   List<Person>findByStreetAddress(String streetAddress);
   Optional<Person> getPersonDetails(Long id);

   Optional<Optional<Person>> addAddressToPerson(Long id, PostalAddress postalAddress);

   Optional<Optional<Person>> addContactMethodToPerson(Long id, ContactMethod contactMethod);
}
