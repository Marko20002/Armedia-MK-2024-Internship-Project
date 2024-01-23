package com.example.demo.web;

import com.example.demo.model.Person;

import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        Person person1 = this.personService.createPerson(person);
        return new ResponseEntity<>(person1, HttpStatus.CREATED);
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> personList = this.personService.listAll();
        return new ResponseEntity<>(personList,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        Person person1 = this.personService.getbyID(id);
        return new ResponseEntity<>(person1,HttpStatus.OK);
    }

    @GetMapping("/email")
    public ResponseEntity<List<Person>> getPersonByEmail(@RequestParam String email) {
        List<Person> persons = this.personService.findByEmail(email)
                ;
        return ResponseEntity.ok().body(persons);
    }

    @GetMapping("/streetadd")
    public ResponseEntity<List<Person>> getByStreetAddress(@RequestParam String streetAddress){
    List<Person> personList = this.personService.findByStreetAddress(streetAddress);
    return ResponseEntity.ok().body(personList);

    }




}
