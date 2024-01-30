package com.example.demo.repository;
import com.example.demo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query("SELECT p FROM Person p JOIN p.contactMethods c WHERE c.type = 'EMAIL' AND c.value = :email")
    List<Person> findByEmail(@Param("email") String email);

    @Query("SELECT p FROM Person p JOIN p.addresses a WHERE a.streetAddress = :streetAddress")
    List<Person> findByStreetAddress(@Param("streetAddress") String streetAddress);

    @Query("SELECT DISTINCT p FROM Person p JOIN p.addresses JOIN p.contactMethods WHERE p.id = :id")
    Optional<Person> findWithAddressandConctact (@Param("id")Long id);

    @Query("SELECT COUNT(a) FROM Person p JOIN p.addresses a WHERE p.id = :personId AND a.streetAddress = :streetAddress")
    int countAddwithStreetAdd(@Param("personId") Long personId, @Param("streetAddress") String streetAddress);
    @Query("SELECT COUNT(cm) FROM Person p JOIN p.contactMethods cm WHERE p.id = :personId AND cm.value = :contactMetodValue")
    int countContactMetod(@Param("personId") Long personId, @Param("contactMetodValue") String contactMetodValue);

}
