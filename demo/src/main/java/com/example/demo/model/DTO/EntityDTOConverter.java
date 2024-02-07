package com.example.demo.model.DTO;

import com.example.demo.model.ContactMethod;
import com.example.demo.model.Person;
import com.example.demo.model.PostalAddress;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class EntityDTOConverter {

    public static Person convertToPersonEntity(PersonDTO personDTO) {
        Person person = new Person();
        person.setName(personDTO.getName());
        person.setFamilyName(personDTO.getFamilyName());
        person.setDateOfBirth(personDTO.getDateOfBirth());
        person.setPlaceOfBirth(personDTO.getPlaceOfBirth());

        person.setContactMethods(convertToContactMethodEntities(personDTO.getContactMethods()));
        person.setAddresses(convertToPostalAddressEntities(personDTO.getAddresses()));

        return person;
    }

    public static PersonDTO convertToPersonDTO(Person person) {
        return PersonDTO.builder()
                .name(person.getName())
                .familyName(person.getFamilyName())
                .dateOfBirth(person.getDateOfBirth())
                .placeOfBirth(person.getPlaceOfBirth())
                .contactMethods(convertToContactMethodDTOList(person.getContactMethods()))
                .addresses(convertToPostalAddressDTOList(person.getAddresses())).build();
    }

    public static List<PersonDTO> convertToPersonDTOList(List<Person> persons) {
        return persons.stream()
                .map(EntityDTOConverter::convertToPersonDTO)
                .collect(Collectors.toList());
    }

    public static PostalAddress convertToPostalAddressEntity(PostalAddressDTO postalAddressDTO) {
        PostalAddress postalAddress = new PostalAddress();
        postalAddress.setStreetAddress(postalAddressDTO.getStreetAddress());
        postalAddress.setCity(postalAddressDTO.getCity());
        postalAddress.setZip(postalAddressDTO.getZip());
        postalAddress.setCountry(postalAddressDTO.getCountry());
        return postalAddress;
    }

    public static ContactMethod convertToContactMethodEntity(ContactMethodDTO contactMethodDTO) {
        ContactMethod contactMethod = new ContactMethod();
        contactMethod.setType(contactMethodDTO.getType());
        contactMethod.setValue(contactMethodDTO.getValue());
        contactMethod.setDescription(contactMethodDTO.getDescription());
        return contactMethod;
    }

    private static List<ContactMethod> convertToContactMethodEntities(List<ContactMethodDTO> contactMethodDTOList) {
        if (contactMethodDTOList == null) {
            return Collections.emptyList();
        }

        return contactMethodDTOList.stream()
                .filter(Objects::nonNull)
                .map(EntityDTOConverter::convertToContactMethodEntity)
                .filter(obj -> true)
                .collect(Collectors.toList());
    }


    private static List<PostalAddress> convertToPostalAddressEntities(List<PostalAddressDTO> postalAddressDTOList) {
        if (postalAddressDTOList == null) {
            return Collections.emptyList();
        }
        return postalAddressDTOList.stream()
                .map(EntityDTOConverter::convertToPostalAddressEntity)
                .collect(Collectors.toList());
    }

    private static List<ContactMethodDTO> convertToContactMethodDTOList(List<ContactMethod> contactMethods) {
        return contactMethods.stream()
                .map(EntityDTOConverter::convertToContactMethodDTO)
                .collect(Collectors.toList());
    }

    private static List<PostalAddressDTO> convertToPostalAddressDTOList(List<PostalAddress> addresses) {
        return addresses.stream()
                .map(EntityDTOConverter::convertToPostalAddressDTO)
                .collect(Collectors.toList());
    }

    private static ContactMethodDTO convertToContactMethodDTO(ContactMethod contactMethod) {
        return ContactMethodDTO.builder()
                .type(contactMethod.getType())
                .value(contactMethod.getValue())
                .description(contactMethod.getDescription()).build();
    }

    private static PostalAddressDTO convertToPostalAddressDTO(PostalAddress postalAddress) {

        return PostalAddressDTO.builder()
                .streetAddress(postalAddress.getStreetAddress())
                .city(postalAddress.getCity())
                .zip(postalAddress.getZip())
                .country(postalAddress.getCountry()).build();
    }
}
