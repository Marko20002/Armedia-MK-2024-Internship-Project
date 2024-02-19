package com.example.demo.service;

import com.example.demo.model.CaseFile;
import com.example.demo.model.DTO.CaseFileDTO;

import java.util.List;
import java.util.Optional;

public interface CaseFileService {
    Optional<CaseFileDTO> createCaseFile(CaseFileDTO caseFileDTO);

    List<CaseFile> listAllCaseFiles();

    CaseFile getCaseFileById(Long caseFileId);

    Optional<CaseFile> updateFile(Long id, CaseFileDTO caseFileDTO);
}