package com.example.demo.service;

import com.example.demo.exception.NoPersonFoundException;
import com.example.demo.model.CaseFile;
import com.example.demo.model.DTO.CaseFileDTO;
import com.example.demo.repository.CaseFileDao;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CaseFileServiceImpl implements CaseFileService {

    private final CaseFileDao caseFileDao;

    public CaseFileServiceImpl(CaseFileDao caseFileDao) {
        this.caseFileDao = caseFileDao;
    }

    @Override
    @Transactional
    public Optional<CaseFileDTO> createCaseFile(CaseFileDTO caseFileDTO) {
        return this.caseFileDao.create(caseFileDTO);
    }

    @Override
    public List<CaseFile> listAllCaseFiles() {
        return this.caseFileDao.listAllCaseFiles();
    }

    @Override
    public CaseFile getCaseFileById(Long caseFileId) {
        try {
            return this.caseFileDao.getCaseFileById(caseFileId);
        } catch (NoResultException ex) {
            throw new NoPersonFoundException(String.format("Case file with id: %d was not found.", caseFileId));
        }
    }

    @Override
    @Transactional
    public Optional<CaseFile> updateFile(Long id, CaseFileDTO caseFileDTO) {
        try {
            return this.caseFileDao.update(id, caseFileDTO);
        } catch (NoResultException ex) {
            throw new NoPersonFoundException(String.format("Case file with id: %d was not found.", id));
        }
    }
}