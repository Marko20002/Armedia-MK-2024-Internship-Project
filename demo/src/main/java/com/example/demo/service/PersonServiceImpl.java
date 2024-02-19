package com.example.demo.service;

import com.example.demo.model.ContactMethod;
import com.example.demo.model.DTO.EntityDTOConverter;
import com.example.demo.model.DTO.PersonDTO;
import com.example.demo.model.Person;
import com.example.demo.model.PostalAddress;
import com.example.demo.repository.PersonDao;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonDao personDao;

    public PersonServiceImpl(PersonDao personDao) {
        this.personDao = personDao;
    }


    @Override
    @Transactional
    public Optional<Person> createPerson(PersonDTO personDTO) {
        Person person = EntityDTOConverter.convertToPersonEntity(personDTO);
        return personDao.save(person);

    }
    @Override
    public List<Person> listAll() {
        return this.personDao.findAll();
    }
    @Override
    public Optional<Person> getbyId(Long id) {
        return Optional.ofNullable(this.personDao.findById(id));
    }

    @Override
    public List<Person> findByEmail(String email) {
        return (List<Person>) personDao.findByEmail(email);
    }

    @Override
    public List<Person> findByStreetAddress(String sAddress) {
        return personDao.findByStreetAddress(sAddress);
    }

    @Override
    public Optional<Person> getPersonDetails(Long id) {
        return Optional.ofNullable(personDao.findById(id));
    }

    @Override
    @Transactional
    public Optional<Optional<Person>> addAddressToPerson(Long id, PostalAddress postalAddress) {
        Person person = personDao.findById(id);

        person.getAddresses().add(postalAddress);
        return Optional.of(this.personDao.save(person));
    }

    @Override
    public Optional<Optional<Person>> addContactMethodToPerson(Long id, ContactMethod contactMethod) {
        Person person = personDao.findById(id);
            person.getContactMethods().add(contactMethod);

        return Optional.of(this.personDao.save(person));
    }


}
