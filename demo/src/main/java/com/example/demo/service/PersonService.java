package com.example.demo.service;

import com.example.demo.model.ContactMethod;
import com.example.demo.model.Person;
import com.example.demo.model.PostalAddress;

import java.util.List;
import java.util.Optional;


public interface PersonService {

   Optional<Person> createPerson(Person person);

   List<Person> listAll();

   Person getbyID(Long ID);

   List<Person>findByEmail(String email);
   List<Person>findByStreetAddress(String streetAddress);
   Optional<Person> getPersonDetails(Long id);

   Optional<Person> addAddressToPerson(Long id, PostalAddress postalAddress);

   Optional<Person> addContactMethodToPerson(Long id, ContactMethod contactMethod);
}
