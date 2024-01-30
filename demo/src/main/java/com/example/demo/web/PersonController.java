package com.example.demo.web;

import com.example.demo.model.ContactMethod;
import com.example.demo.model.Person;
import com.example.demo.model.PostalAddress;
import com.example.demo.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/create")
    public ResponseEntity<Person> createPerson(@Valid @RequestBody Person person) {
        //Optional<Person> person1 = this.personService.createPerson(person);
        //return new ResponseEntity<>(person1, HttpStatus.CREATED);
        return this.personService.createPerson(person)
                .map(p -> ResponseEntity.ok().body(p))
                .orElseGet(() -> ResponseEntity.badRequest().build());
//        for(PostalAddress address: person.getAddresses())
//            address.setPerson(person);
//        return this.personService.createPerson(person);
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> personList = this.personService.listAll();
        return new ResponseEntity<>(personList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        Person person = this.personService.getbyID(id);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @GetMapping("/email")
    public ResponseEntity<List<Person>> getPersonByEmail(@RequestParam String email) {
        List<Person> persons = this.personService.findByEmail(email);
        return ResponseEntity.ok().body(persons);
    }

    @GetMapping("/streetadd")
    public ResponseEntity<List<Person>> getByStreetAddress(@RequestParam String streetAddress) {
        List<Person> personList = this.personService.findByStreetAddress(streetAddress);
        return ResponseEntity.ok().body(personList);

    }

    @GetMapping("/getPersonDetails/{id}")
    public ResponseEntity<Optional<Person>> getPersonDetails(@PathVariable Long id) {
        Optional<Person> person = this.personService.getPersonDetails(id);
        return ResponseEntity.ok().body(person);
    }
    @PostMapping("/{id}/addAddress")
    public ResponseEntity<Optional<Person>> addAddressToPerson(@PathVariable Long id, @RequestBody PostalAddress postalAddress)
    {
        Optional<Person> person = this.personService.addAddressToPerson(id,postalAddress);
        return ResponseEntity.ok().body(person);

       //boolean success = this.personService.addAddressToPerson(id, postalAddress);
        //return success ? new ResponseEntity<>(HttpStatus.CREATED):new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/{id}/addContactMetod")
    public ResponseEntity<Optional<Person>>addContactMethodToPerson(@PathVariable Long id, @RequestBody @Valid ContactMethod contactMethod)
    {
        Optional<Person>person = this.personService.addContactMethodToPerson(id,contactMethod);
        return ResponseEntity.ok().body(person);
//        boolean success = this.personService.addContactMethodToPerson(id,contactMethod);
//        return success ? new ResponseEntity<>(HttpStatus.CREATED):new ResponseEntity<>(HttpStatus.BAD_REQUEST);
}


}
