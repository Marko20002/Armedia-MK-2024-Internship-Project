package com.example.demo.web;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/person")
public class PersonController {
    private final PersonService personService;
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/create")
    public Person createPerson(@RequestBody Person person) {
        return personService.createPerson(person);
    }

    @GetMapping("/listAll")
    public List<Person> getAllPersons() {
        return personService.listAll();
    }
    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable Long id) {
        return personService.getbyID(id);
    }
}
