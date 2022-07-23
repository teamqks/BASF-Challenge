package com.basf.challenge.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.basf.challenge.dtos.PatentDTO;
import com.basf.challenge.entities.Patent;
import com.basf.challenge.mappers.PatentMapper;
import com.basf.challenge.repositories.PatentRepository;
import com.basf.challenge.services.IExtractionService;
import com.basf.challenge.utils.MockUtils;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@SpringBootTest
@AutoConfigureMockMvc
public class ExtractionServiceTest {

    @MockBean
    private PatentRepository patentRepository;

    @Autowired
    private IExtractionService extractionService;

    @Autowired
    private PatentMapper patentMapper;

    @Test
    public void getListPatents() throws Exception {
        MockUtils mockUtils = new MockUtils();
        List<Patent> listPatents = mockUtils.createListPatentWithoutNer();
        Mockito.when(patentRepository.findAll()).thenReturn(listPatents);
        List<PatentDTO> listPatentsDTOs = patentRepository.findAll().stream()
                .map(patentMapper::patentToPatentDTO).collect(Collectors.toList());
        assertEquals(listPatentsDTOs, extractionService.listPatents());
    }

    @Test
    public void getPatent() throws Exception {
        MockUtils mockUtils = new MockUtils();
        String id = "id_test";
        Patent patent = mockUtils.createPatentWithoutNer(id);
        PatentDTO patentDTO = patentMapper.patentToPatentDTO(patent);
        Mockito.when(patentRepository.findById(id)).thenReturn(Optional.of(patent));
        assertEquals(patentDTO, extractionService.getPatent(id));
    }

    @Test
    public void when_NotExistsPatent_GetPatent() throws Exception {
        String id = "idNotExists_test";
        Mockito.when(patentRepository.findById(id)).thenThrow(NoSuchElementException.class);
        assertThrows(NoSuchElementException.class, () -> {
            extractionService.getPatent(id);
        });
    }

    @Test
    public void deletePatent() throws Exception {
        MockUtils mockUtils = new MockUtils();
        String id = "id_test";
        Patent patent = mockUtils.createPatentWithoutNer(id);
        Mockito.when(patentRepository.findById(id)).thenReturn(Optional.of(patent));
        extractionService.delete(id);
        Mockito.verify(patentRepository, Mockito.times(1)).deleteById(id);
    }

    @Test
    public void when_NotExistsPatent_DeletePatent() throws Exception {
        String id = "idNotExists_test";
        Mockito.when(patentRepository.findById(id)).thenThrow(NoSuchElementException.class);
        assertThrows(NoSuchElementException.class, () -> {
            extractionService.delete(id);
        });
    }

    @Test
    public void deleteAll() throws Exception {
        MockUtils mockUtils = new MockUtils();
        List<Patent> patents = mockUtils.createListPatentWithoutNer();
        Mockito.when(patentRepository.findAll()).thenReturn(patents);
        extractionService.deleteAll();
        Mockito.verify(patentRepository, Mockito.times(1)).deleteAll();
    }

}
