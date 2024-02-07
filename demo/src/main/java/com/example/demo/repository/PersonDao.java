package com.example.demo.repository;

import com.example.demo.exception.NoPersonFoundException;
import com.example.demo.model.Person;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

import static com.example.demo.model.Person.*;

@Repository
public class PersonDao extends EntityDao<Person> {

    public PersonDao() {
        super(Person.class);
    }

    public Person findByEmail(String email) {
        Query namedQuery = entityManager.createNamedQuery(PERSON_FIND_BY_EMAIL, Person.class);
        namedQuery.setParameter("email", email);
        try {
            return (Person) namedQuery.getSingleResult();
        } catch (NoResultException ex) {
            throw new NoPersonFoundException(String.format("person with %s not found", email));
        }
    }

    public List<Person> findAll() {
        Query namedQuery = entityManager.createNamedQuery(PERSON_FIND_ALL, List.class);

        return namedQuery.getResultList();
    }

    public Person findById(Long id) {
        Query namedQuery = entityManager.createNamedQuery(PERSON_FIND_BY_ID, Person.class);
        namedQuery.setParameter("id", id);
        try {
            return (Person) namedQuery.getSingleResult();
        } catch (NoResultException ex) {
            throw new NoPersonFoundException(String.format("Person with ID '%d' not found", id));
        }
    }

    public List<Person> findByStreetAddress(String streetAddress) {
        Query namedQuery = entityManager.createNamedQuery(PERSON_FIND_BY_STREET_ADDRESS, Person.class);
        namedQuery.setParameter("streetAddress", streetAddress);
        return namedQuery.getResultList();
    }

}
