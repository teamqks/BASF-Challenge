package com.basf.challenge.mappers;

import com.basf.challenge.dtos.PatentDTO;
import com.basf.challenge.entities.Patent;
import com.basf.challenge.entities.xml.PatentInput;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class PatentMapper {

    private ModelMapper modelMapper;

    public PatentMapper(){
        this.modelMapper = new ModelMapper();
        this.modelMapper.addMappings(new PropertyMap<PatentInput, Patent>() {
            @Override
            protected void configure() {
                map().setId(null);
                map().setTitle(source.getDateAndTitle().getTitle());
                map().setYear(source.getDateAndTitle().getDate().getDate().getDate());
                map().setAbstrct(source.getAbstrct().getAbstrct());
            }
        });
    }

    public Patent patentInputToPatent(PatentInput patentInput) {
        return modelMapper.map(patentInput, Patent.class);
    }

    public PatentDTO patentToPatentDTO(Patent patent) {
        return modelMapper.map(patent, PatentDTO.class);
    }

}
