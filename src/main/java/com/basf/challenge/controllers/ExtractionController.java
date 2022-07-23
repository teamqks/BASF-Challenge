package com.basf.challenge.controllers;

import com.basf.challenge.dtos.PatentDTO;
import com.basf.challenge.services.IExtractionService;
import com.basf.challenge.services.IFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/extractions")
@Slf4j
public class ExtractionController {

    private final IExtractionService extractionService;
    private final IFileService fileService;

    public ExtractionController(IExtractionService extractionService, IFileService fileService) {
        this.extractionService = extractionService;
        this.fileService = fileService;
    }

    @GetMapping("/")
    public List<PatentDTO> listPatents() {
        return extractionService.listPatents();
    }

    @GetMapping("/{id}")
    public PatentDTO getPatent(@PathVariable String id) {
        return extractionService.getPatent(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePatent(@PathVariable String id) {
        extractionService.delete(id);
    }

    @DeleteMapping("/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll() {
        extractionService.deleteAll();
    }

    @PostMapping("/execute")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void execute(@RequestParam("files") List<MultipartFile> files) throws JAXBException, IOException {
        extractionService.execute(files);
    }

}
