package com.example.demo.service;

import com.example.demo.model.ContactMethod;
import com.example.demo.model.Person;
import com.example.demo.model.PostalAddress;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.PostalAddressRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    private final PostalAddressRepository postalAddressRepository;

    public PersonServiceImpl(PersonRepository personRepository, PostalAddressRepository postalAddressRepository) {
        this.personRepository = personRepository;
        this.postalAddressRepository = postalAddressRepository;
    }

    @Override
    public Optional<Person> createPerson(Person person) {
//        for (PostalAddress address : person.getAddresses())
//            address.setPerson(person);
//        for (ContactMethod contactMethod : person.getContactMethods())
//            contactMethod.setPerson(person);
        personRepository.save(person);
        return Optional.of(this.personRepository.save(person));
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
    public List<Person> findByStreetAddress(String sAddress) {
        return personRepository.findByStreetAddress(sAddress);
    }

    @Override
    public Optional<Person> getPersonDetails(Long id) {
        return personRepository.findWithAddressandConctact(id);
    }

    @Override
    public Optional<Person> addAddressToPerson(Long personId, PostalAddress postalAddress) {
        if (personId == null || postalAddress == null)
            return Optional.empty();

        Person person = personRepository.findById(personId).orElse(null);


        assert person != null;
        person.getAddresses().add(postalAddress);
        this.personRepository.save(person);

        if (!isAddressUniqueForPerson(personId, postalAddress.getStreetAddress())) {
            return Optional.empty();
        }
        return Optional.of(person);
    }

    private boolean isAddressUniqueForPerson(Long id, String address) {
        return personRepository.countAddwithStreetAdd(id, address) == 0;
    }

    @Override
    public Optional<Person> addContactMethodToPerson(Long personId, ContactMethod contactMethod) {
        if (personId == null || contactMethod == null)
            return Optional.empty();

        Person person = personRepository.findById(personId).orElse(null);
        if (person == null)
            return Optional.empty();
        if (!isContactMethodUnique(personId, contactMethod.getValue())) {
            return Optional.empty(); // Contact method is not unique for the person
        }
        contactMethod.setPerson(person);
        person.getContactMethods().add(contactMethod);

        return Optional.of(person);
    }
    private boolean isContactMethodUnique(Long id, String contactMethod)
    {
        return personRepository.countContactMetod(id,contactMethod)==0;
    }
}
