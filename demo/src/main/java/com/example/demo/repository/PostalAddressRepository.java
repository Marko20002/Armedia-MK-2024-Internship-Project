package com.example.demo.repository;

import com.example.demo.model.PostalAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostalAddressRepository extends JpaRepository<PostalAddress, Long> {
}
