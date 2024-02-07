package com.example.demo.web;


import com.example.demo.exception.PersonAlreadyExistsException;
import com.example.demo.model.ContactMethod;
import com.example.demo.model.DTO.PersonDTO;
import com.example.demo.model.Person;
import com.example.demo.model.PostalAddress;
import com.example.demo.service.PersonService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }
    @PostMapping("/create")
    public ResponseEntity<Object> createPerson(@RequestBody @Valid PersonDTO personDTO) {
        try {
            return new ResponseEntity<Object>(this.personService.createPerson(personDTO), HttpStatus.CREATED);
        }catch (DataIntegrityViolationException ex)
        {
            throw new PersonAlreadyExistsException("this already exist");
        }
    }


    @GetMapping("/listAll")
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> personList = this.personService.listAll();
        return new ResponseEntity<>(personList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        Optional<Person> person = personService.getbyId(id);
        if (!person.isPresent())
            throw new NoSuchElementException();
        return ResponseEntity.of(person);

    }


    @GetMapping("/email")
    public ResponseEntity<?> getPersonByEmail(@RequestParam String email) {
        List<Person> persons = this.personService.findByEmail(email);
        return new ResponseEntity<>(persons, HttpStatus.OK);


    }

    @GetMapping("/streetAdd")
    public ResponseEntity<?> getByStreetAddress(@RequestParam String streetAddress) {
        List<Person> personList = this.personService.findByStreetAddress(streetAddress);
        return new ResponseEntity<>(personList, HttpStatus.OK);
    }

    @GetMapping("/getPersonDetails/{id}")
    public ResponseEntity<?> getPersonDetails(@PathVariable Long id) {
        Optional<Person> person = personService.getPersonDetails(id);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PostMapping("/addAddress/{id}")
    public ResponseEntity<Object> addPostalAddress(@PathVariable Long id,
                                              @RequestBody @Valid PostalAddress postalAddress) {
        Optional<Optional<Person>> person = personService.addAddressToPerson(id, postalAddress);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PostMapping("/addContactMetod/{id}")
    public ResponseEntity<?> addContactMethod(@PathVariable Long id,
                                              @RequestBody @Valid ContactMethod contactMethod) {
        Optional<Optional<Person>> person = personService.addContactMethodToPerson(id, contactMethod);
        return new ResponseEntity<>(person, HttpStatus.OK);

    }
}
