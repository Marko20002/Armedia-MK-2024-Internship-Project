package com.example.demo.repository;

import com.example.demo.model.CaseFile;
import com.example.demo.model.DTO.CaseFileDTO;
import com.example.demo.model.DTO.EntityDTOConverter;
import com.example.demo.model.Person;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Component
public class CaseFileDao {

    @PersistenceContext
    private final EntityManager entityManager;

    public CaseFileDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<CaseFileDTO> create(CaseFileDTO caseFileDTO) {
        if (caseFileDTO == null) {
            return Optional.empty();
        }
        Person person;
        Long personId = caseFileDTO.getPersonId();

        if (personId != null) {
            person = entityManager.find(Person.class, personId);
        } else {
            person = EntityDTOConverter.convertToPersonEntity(caseFileDTO.getPersonDTO());
            entityManager.persist(person);
        }
        CaseFile caseFile = EntityDTOConverter.convertToCaseFileEntity(caseFileDTO);
        caseFile.setPerson(person);
        entityManager.persist(caseFile);

        caseFileDTO.setId(caseFile.getId());
        return Optional.of(caseFileDTO);
    }

    public List<CaseFile> listAllCaseFiles() {
        return entityManager.createNamedQuery("CaseFile.findAll", CaseFile.class).getResultList();
    }

    public CaseFile getCaseFileById(Long id) {
        return entityManager.createNamedQuery("CaseFile.findById", CaseFile.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public Optional<CaseFile> update(Long id, CaseFileDTO updatedFileDTO) {
        CaseFile caseFile = this.entityManager.createNamedQuery("CaseFile.findById", CaseFile.class)
                .setParameter("id", id).getSingleResult();
        if (updatedFileDTO.getTitle() != null) {
            caseFile.setTitle(updatedFileDTO.getTitle());
        }
        if (updatedFileDTO.getCaseNumber() != null) {
            caseFile.setCaseNumber(Integer.valueOf(updatedFileDTO.getCaseNumber()));
        }
        if (updatedFileDTO.getIncidentDate() != null) {
            caseFile.setIncidentDate(updatedFileDTO.getIncidentDate());
        }
        this.entityManager.persist(caseFile);
        return Optional.of(caseFile);
    }
}