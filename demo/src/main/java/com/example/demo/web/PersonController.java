package web;

import model.Person;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import service.PersonService;



@RestController
@RequestMapping("/persons")
public class PersonController {


    @Autowired
    private PersonService personService;
    @PostMapping
    public Person createPerson(@RequestBody Person person) {
        return personService.createPerson(person);
    }
}
