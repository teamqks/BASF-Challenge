package com.basf.challenge.services;

import com.basf.challenge.entities.Patent;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface IFileService {

    List<Patent> createPatentsFromFiles(List<MultipartFile> files) throws JAXBException, IOException;

}
