package com.example.demo.web;


import com.example.demo.exception.NoPersonFoundException;
import com.example.demo.exception.PersonAlreadyExistsException;
import com.example.demo.model.*;
import com.example.demo.model.DTO.PersonDTO;
import com.example.demo.model.DTO.UserDTO;
import com.example.demo.service.PersonService;
import com.example.demo.service.UserDetailsServise;
import com.example.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private final PersonService personService;
    @Autowired
    private final UserDetailsServise userDetailsServise;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private final JwtUtil jwtTokenUtil;

    public PersonController(PersonService personService, UserDetailsServise userDetailsServise, AuthenticationManager authenticationManager, JwtUtil jwtTokenUtil) {
        this.personService = personService;
        this.userDetailsServise = userDetailsServise;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
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
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO user) throws Exception {

        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword()));
        }catch (BadCredentialsException ex){

            throw new Exception("Incorect username or passwored", ex);
        }

        final MyUserDetails myUserDetails = userDetailsServise.loadUserByUsername(user.getUserName());
        final String jwt = jwtTokenUtil.generateToken(myUserDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));

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
            throw new NoPersonFoundException("No Person Found");
        return ResponseEntity.of(person);

    }


    @GetMapping("/email")
    public ResponseEntity<?> getPersonByEmail(@RequestParam String email) {
        List<Person> persons = this.personService.findByEmail(email);
        return new ResponseEntity<>(persons, HttpStatus.OK);


    }
    @GetMapping("/car")
    public String marko()
    {return ("<h1>Welcome to Our Website!</h1>"); }
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
