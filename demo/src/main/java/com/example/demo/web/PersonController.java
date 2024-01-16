package com.example.demo.web;

import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import com.example.demo.service.PersonService;

import java.util.List;


@RestController
@RequestMapping("/api/persons")
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/create")
    public Person createPerson(@RequestBody Person person) {
        return personService.createPerson(person);
    }
    @GetMapping("/listall")
    public List<Person> getAllPersons() {
        return personService.listAll();
    }
    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable Long id) {
        return personService.getbyID(id);
    }
}
