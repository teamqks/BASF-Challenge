package com.basf.challenge.controllers;

import com.basf.challenge.dtos.PatentDTO;
import com.basf.challenge.services.IExtractionService;
import com.basf.challenge.utils.MockUtils;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ExtractionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IExtractionService extractionService;

    private final String URI = "/api/v1/extractions/";

    @Test
    public void getListPatents() throws Exception {
        MockUtils mockUtils = new MockUtils();
        List<PatentDTO> listPatents = mockUtils.createListPatentDTOWithoutNer();
        Mockito.when(extractionService.listPatents()).thenReturn(listPatents);
        mockMvc.perform(get(URI).header("BASF-Auth","secret"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":\"id_test1\",\"year\":year_test,\"title\":title_test,\"abstrct\":abstract_test,\"nerOutput\":[]}," +
                        "{\"id\":\"id_test2\",\"year\":year_test,\"title\":title_test,\"abstrct\":abstract_test,\"nerOutput\":[]}]"));
    }

    @Test
    public void when_NotAuthorized_GetListPatents() throws Exception {
        MockUtils mockUtils = new MockUtils();
        List<PatentDTO> listPatents = mockUtils.createListPatentDTOWithoutNer();
        Mockito.when(extractionService.listPatents()).thenReturn(listPatents);
        mockMvc.perform(get(URI))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("Not authorized"));
    }

    @Test
    public void when_AnyOtherException_GetListPatents() throws Exception {
        Mockito.when(extractionService.listPatents()).thenThrow(new RuntimeException());
        mockMvc.perform(get(URI).header("BASF-Auth","secret"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Internal error"));
    }

    @Test
    public void getPatent() throws Exception {
        MockUtils mockUtils = new MockUtils();
        String id = "id_test";
        PatentDTO patentDTO = mockUtils.createPatentDTOWithoutNer(id);
        Mockito.when(extractionService.getPatent(id)).thenReturn(patentDTO);
        mockMvc.perform(get(URI+id).header("BASF-Auth","secret"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":\"id_test\",\"year\":year_test,\"title\":title_test,\"abstrct\":abstract_test,\"nerOutput\":[]}"));
    }

    @Test
    public void when_NotAuthorized_GetPatent() throws Exception {
        MockUtils mockUtils = new MockUtils();
        String id = "id_test";
        PatentDTO patentDTO = mockUtils.createPatentDTOWithoutNer(id);
        Mockito.when(extractionService.getPatent(id)).thenReturn(patentDTO);
        mockMvc.perform(get(URI+id))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("Not authorized"));
    }

    @Test
    public void when_NotExistsPatent_GetPatent() throws Exception {
        String id = "idNotExists_test";
        Mockito.when(extractionService.getPatent(id)).thenThrow(new NoSuchElementException());
        mockMvc.perform(get(URI+id).header("BASF-Auth","secret"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Not found"));
    }

    @Test
    public void when_AnyOtherException_GetPatent() throws Exception {
        String id = "id_test";
        Mockito.when(extractionService.getPatent(id)).thenThrow(new RuntimeException());
        mockMvc.perform(get(URI+id).header("BASF-Auth","secret"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Internal error"));
    }

    @Test
    public void deletePatent() throws Exception {
        String id = "id_test";
        mockMvc.perform(MockMvcRequestBuilders.delete(URI+id).header("BASF-Auth","secret"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void when_NotAuthorized_DeletePatent() throws Exception {
        String id = "id_test";
        mockMvc.perform(MockMvcRequestBuilders.delete(URI+id))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("Not authorized"));
    }

    @Test
    public void when_NotExistsPatent_DeletePatent() throws Exception {
        String id = "idNotExists_test";
        Mockito.doThrow(new NoSuchElementException()).when(extractionService).delete(id);
        mockMvc.perform(MockMvcRequestBuilders.delete(URI+id).header("BASF-Auth","secret"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Not found"));
    }

    @Test
    public void when_AnyOtherException_DeletePatent() throws Exception {
        String id = "id_test";
        Mockito.doThrow(new RuntimeException()).when(extractionService).delete(id);
        mockMvc.perform(MockMvcRequestBuilders.delete(URI+id).header("BASF-Auth","secret"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Internal error"));
    }

    @Test
    public void deleteAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(URI).header("BASF-Auth","secret"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void when_NotAuthorized_DeleteAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(URI))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("Not authorized"));
    }

    @Test
    public void when_AnyOtherException_DeleteAll() throws Exception {
        Mockito.doThrow(new RuntimeException()).when(extractionService).deleteAll();
        mockMvc.perform(MockMvcRequestBuilders.delete(URI).header("BASF-Auth","secret"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Internal error"));
    }

}
