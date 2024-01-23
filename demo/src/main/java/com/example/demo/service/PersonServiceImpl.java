package com.example.demo.service;

import com.example.demo.model.ContactMethod;
import com.example.demo.model.Person;
import com.example.demo.model.PostalAddress;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.PostalAddressRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service

public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    private final PostalAddressRepository postalAddressRepository;

    public PersonServiceImpl(PersonRepository personRepository, PostalAddressRepository postalAddressRepository) {
        this.personRepository = personRepository;
        this.postalAddressRepository = postalAddressRepository;
    }

    @Override
    @Transactional
    public Person createPerson(Person person) {

        for (PostalAddress address : person.getAddresses())
            address.setPerson(person);
        for (ContactMethod contactMethod : person.getContactMethods())
            contactMethod.setPerson(person);
        personRepository.save(person);
        return person;
    }

    @Override
    public List<Person> listAll() {
        return this.personRepository.findAll();
    }

    @Override
    public Person getbyID(Long ID) {
        return personRepository.findById(ID).orElse(null);
    }

    @Override
    public List<Person> findByEmail(String email) {
       return personRepository.findByEmail(email);
    }

    @Override
    public List<Person> findByStreetAddress(String streetAddress) {
        return personRepository.findByStreetAddress(streetAddress);
    }


}
