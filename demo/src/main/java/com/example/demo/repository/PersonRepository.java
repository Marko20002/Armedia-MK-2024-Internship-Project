package com.example.demo.repository;
import com.example.demo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query("SELECT p FROM Person p JOIN p.contactMethods c WHERE c.type = 'EMAIL' AND c.value = :email")
    List<Person> findByEmail(@Param("email") String email);

    @Query("SELECT p FROM Person p JOIN p.addresses a WHERE a.streetAddress = :streetAddress")
    List<Person> findByStreetAddress(@Param("streetAddress") String streetAddress);
}
