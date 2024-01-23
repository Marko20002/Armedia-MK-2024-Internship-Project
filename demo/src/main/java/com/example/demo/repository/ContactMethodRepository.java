package com.example.demo.repository;

import com.example.demo.model.ContactMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactMethodRepository extends JpaRepository<ContactMethod,Long> {
}
