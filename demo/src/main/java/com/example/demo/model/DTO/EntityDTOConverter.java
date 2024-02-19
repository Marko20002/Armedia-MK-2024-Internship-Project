package com.example.demo.model.DTO;

import com.example.demo.model.*;

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
        person.setDemoUser(convertToUserEntity(personDTO.getUser()));
        return person;
    }

    public static PersonDTO convertToPersonDTO(Person person) {
        return PersonDTO.builder()
                .name(person.getName())
                .familyName(person.getFamilyName())
                .dateOfBirth(person.getDateOfBirth())
                .placeOfBirth(person.getPlaceOfBirth())
                .contactMethods(convertToContactMethodDTOList(person.getContactMethods()))
                .addresses(convertToPostalAddressDTOList(person.getAddresses()))
                .user(convertToUserDTO(person.getDemoUser()))
                .build();
    }

    public static List<PersonDTO> convertToPersonDTOList(List<Person> persons) {
        return persons.stream()
                .map(EntityDTOConverter::convertToPersonDTO)
                .collect(Collectors.toList());
    }
    public static DemoUser convertToUserEntity(UserDTO userDTO)
    {
        DemoUser demoUser = new DemoUser();
        demoUser.setUserName(userDTO.getUserName());
        demoUser.setPassword(userDTO.getPassword());
        demoUser.setActive(userDTO.isActive());
        demoUser.setRole(userDTO.getRole());
        return demoUser;
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
        return contactMethodDTOList.stream()
                .filter(Objects::nonNull)
                .map(EntityDTOConverter::convertToContactMethodEntity)
                .collect(Collectors.toList());
    }


    private static List<PostalAddress> convertToPostalAddressEntities(List<PostalAddressDTO> postalAddressDTOList) {
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
    private static UserDTO convertToUserDTO(DemoUser demoUser)
    {
        return UserDTO.builder()
                .role((demoUser.getRole()))
                .active(demoUser.isActive())
                .userName(demoUser.getUserName())
                .password(demoUser.getPassword()).build();
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

    public static CaseFile convertToCaseFileEntity(CaseFileDTO caseFileDTO) {
        if (caseFileDTO == null) {
            return null;
        }
        CaseFile caseFile = new CaseFile();
        caseFile.setCaseNumber(Integer.valueOf(caseFileDTO.getCaseNumber()));
        caseFile.setTitle(caseFileDTO.getTitle());
        caseFile.setIncidentDate(caseFileDTO.getIncidentDate());
        return caseFile;
    }
}
