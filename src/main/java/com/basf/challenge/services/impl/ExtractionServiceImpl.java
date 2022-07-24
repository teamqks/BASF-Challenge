package com.basf.challenge.services.impl;

import com.basf.challenge.dtos.PatentDTO;
import com.basf.challenge.entities.NamedEntities;
import com.basf.challenge.entities.Patent;
import com.basf.challenge.exceptions.InternalException;
import com.basf.challenge.mappers.PatentMapper;
import com.basf.challenge.ner.NerProcess;
import com.basf.challenge.repositories.PatentRepository;
import com.basf.challenge.services.IExtractionService;
import com.basf.challenge.services.IFileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExtractionServiceImpl implements IExtractionService {

    private final IFileService fileService;
    private final PatentRepository patentRepository;
    private final NerProcess nerProcess;
    private final PatentMapper patentMapper;

    public ExtractionServiceImpl(IFileService fileService, PatentRepository patentRepository,
                                 NerProcess nerProcess, PatentMapper patentMapper) {
        this.fileService = fileService;
        this.patentRepository = patentRepository;
        this.nerProcess = nerProcess;
        this.patentMapper = patentMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PatentDTO> listPatents() {
        List<Patent> listPatents = patentRepository.findAll();
        List<PatentDTO> listPatentsDTO = listPatents.stream()
                .map(patentMapper::patentToPatentDTO)
                .collect(Collectors.toList());
        return listPatentsDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public PatentDTO getPatent(String id) {
        Optional<Patent> patent = patentRepository.findById(id);
        return patentMapper.patentToPatentDTO(patent.orElseThrow());
    }

    @Override
    @Transactional
    public void delete(String id) {
        if(patentRepository.findById(id) != null) {
            patentRepository.deleteById(id);
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    @Transactional
    public void deleteAll() {
        patentRepository.deleteAll();
    }

    @Override
    @Transactional
    public void execute(List<MultipartFile> files) throws JAXBException, IOException {
        List<Patent> patents = fileService.createPatentsFromFiles(files);
        if(patents.isEmpty()) {
            throw new InternalException("No files to process");
        }
        patents.stream().forEach(patent -> {
            List<NamedEntities> list = nerProcess.getNamedEntities(patent.getAbstrct());
            patent.setNerOutput(list);
            patent.setYear(patent.getYear().substring(0,4));
        });
        patentRepository.saveAll(patents);
    }

}
