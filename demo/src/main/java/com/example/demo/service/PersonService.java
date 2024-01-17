package com.example.demo.service;

import com.example.demo.model.Person;

import java.util.List;


public interface PersonService {

   Person createPerson(Person person);

   List<Person> listAll();

   Person getbyID(Long ID);


}