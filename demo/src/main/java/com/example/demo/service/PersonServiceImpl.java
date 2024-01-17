package com.example.demo.service;

import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repository.PersonRepository;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {


    @Autowired
    private PersonRepository personRepository;


    @Override
    public Person createPerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public List<Person> listAll() {
        return this.personRepository.findAll();
    }

    @Override
    public Person getbyID(Long ID) {
        return personRepository.findById(ID).orElse(null);
    }


}
