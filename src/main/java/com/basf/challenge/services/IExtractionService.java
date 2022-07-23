package com.basf.challenge.services;

import com.basf.challenge.dtos.PatentDTO;

import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface IExtractionService {

    List<PatentDTO> listPatents();

    PatentDTO getPatent(String id);

    void delete(String id);

    void deleteAll();

    void execute(List<MultipartFile> files) throws JAXBException, IOException;

}
