package com.basf.challenge.services.impl;

import com.basf.challenge.entities.Patent;
import com.basf.challenge.entities.xml.PatentInput;
import com.basf.challenge.mappers.PatentMapper;
import com.basf.challenge.services.IFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class FileServiceImpl implements IFileService {

    private final PatentMapper mapper;

    public FileServiceImpl(PatentMapper patentMapper) {
        this.mapper = patentMapper;
    }

    @Override
    public List<Patent> createPatentsFromFiles(List<MultipartFile> files) throws JAXBException, IOException {
        List<Patent> patents = new ArrayList<>();
        JAXBContext context = JAXBContext.newInstance(PatentInput.class);
        Unmarshaller xmlInJava = context.createUnmarshaller();
        for(MultipartFile file : files) {
            File convFile = new File(file.getOriginalFilename());
            FileOutputStream fileOutputStream = new FileOutputStream( convFile );
            fileOutputStream.write(file.getBytes());
            fileOutputStream.close();
            PatentInput patentInput = (PatentInput) xmlInJava.unmarshal(convFile);
            convFile.delete();
            Patent patent = mapper.patentInputToPatent(patentInput);
            if(isPatentInvalid(patent)) {
                log.error("Parsing error in file: "+file.getOriginalFilename());
                continue;
            }
            patents.add(patent);
        }
        return patents;
    }

    private boolean isPatentInvalid(Patent patent) {
        if(patent.getTitle() == null || patent.getAbstrct() == null || patent.getYear() == null) {
            return true;
        }
        return false;
    }

}
