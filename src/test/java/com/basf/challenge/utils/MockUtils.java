package com.basf.challenge.utils;

import com.basf.challenge.dtos.PatentDTO;
import com.basf.challenge.entities.NamedEntities;
import com.basf.challenge.entities.Patent;
import com.basf.challenge.entities.xml.*;

import java.util.ArrayList;
import java.util.List;

public class MockUtils {

    public PatentDTO createPatentDTOWithNer(String id) {
        PatentDTO patentDTO = new PatentDTO();
        NamedEntities namedEntities = new NamedEntities();
        namedEntities.setText("text_test");
        namedEntities.setEntityType("entityType_test");
        List<NamedEntities> list = new ArrayList<>();
        list.add(namedEntities);
        patentDTO.setId(id);
        patentDTO.setTitle("title_test");
        patentDTO.setYear("year_test");
        patentDTO.setAbstrct("abstract_test");
        patentDTO.setNerOutput(list);
        return patentDTO;
    }

    public PatentDTO createPatentDTOWithoutNer(String id) {
        PatentDTO patentDTO = new PatentDTO();
        patentDTO.setId(id);
        patentDTO.setTitle("title_test");
        patentDTO.setYear("year_test");
        patentDTO.setAbstrct("abstract_test");
        patentDTO.setNerOutput(new ArrayList<>());
        return patentDTO;
    }

    public List<PatentDTO> createListPatentDTOWithoutNer() {
        String id_1 = "id_test1";
        String id_2 = "id_test2";
        PatentDTO patentDTO_1 = createPatentDTOWithoutNer(id_1);
        PatentDTO patentDTO_2 = createPatentDTOWithoutNer(id_2);
        List<PatentDTO> listPatents = new ArrayList<>();
        listPatents.add(patentDTO_1);
        listPatents.add(patentDTO_2);
        return listPatents;
    }

    public Patent createPatentWithoutNer(String id) {
        Patent patent = new Patent();
        patent.setId(id);
        patent.setTitle("title_test");
        patent.setYear("year_test");
        patent.setAbstrct("abstract_test");
        patent.setNerOutput(new ArrayList<>());
        return patent;
    }

    public List<Patent> createListPatentWithoutNer() {
        String id_1 = "id_test1";
        String id_2 = "id_test2";
        Patent patent_1 = createPatentWithoutNer(id_1);
        Patent patent_2 = createPatentWithoutNer(id_2);
        List<Patent> listPatents = new ArrayList<>();
        listPatents.add(patent_1);
        listPatents.add(patent_2);
        return listPatents;
    }

    public PatentInput createPatentInput() {
        PatentInput patentInput = new PatentInput();
        AbstractInput abstractInput = new AbstractInput();
        abstractInput.setAbstrct("abstract_test");
        DateAndTitleInput dateAndTitleInput = new DateAndTitleInput();
        DateIntermInput dateIntermInput = new DateIntermInput();
        DateLeafInput dateLeafInput = new DateLeafInput();
        dateLeafInput.setDate("year_test");
        dateIntermInput.setDate(dateLeafInput);
        dateAndTitleInput.setDate(dateIntermInput);
        dateAndTitleInput.setTitle("title_test");
        patentInput.setAbstrct(abstractInput);
        patentInput.setDateAndTitle(dateAndTitleInput);
        return patentInput;
    }
}
