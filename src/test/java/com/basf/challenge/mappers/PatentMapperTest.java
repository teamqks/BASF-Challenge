package com.basf.challenge.mappers;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.basf.challenge.dtos.PatentDTO;
import com.basf.challenge.entities.Patent;
import com.basf.challenge.entities.xml.PatentInput;
import com.basf.challenge.utils.MockUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public class PatentMapperTest {

    private PatentMapper patentMapper;

    private final String ID = "test_id";

    @Test
    public void when_MapPatentInputToPatent_Then_OK() {
        patentMapper = new PatentMapper();
        MockUtils mockUtils = new MockUtils();
        PatentInput patentInput = mockUtils.createPatentInput();
        Patent patent = mockUtils.createPatentWithoutNer(ID);
        Patent patentmapped = patentMapper.patentInputToPatent(patentInput);
        patentmapped.setId(ID);
        patentmapped.setNerOutput(new ArrayList<>());
        assertNotNull(patentmapped);
        assertEquals(patent,patentmapped);
    }

    @Test
    public void when_MapPatentToPatentDTO_Then_OK() {
        patentMapper = new PatentMapper();
        MockUtils mockUtils = new MockUtils();
        Patent patent = mockUtils.createPatentWithoutNer(ID);
        PatentDTO patentDTO = mockUtils.createPatentDTOWithoutNer(ID);
        PatentDTO patentDTOmapped = patentMapper.patentToPatentDTO(patent);
        assertNotNull(patentDTOmapped);
        assertEquals(patentDTO,patentDTOmapped);
    }

}
