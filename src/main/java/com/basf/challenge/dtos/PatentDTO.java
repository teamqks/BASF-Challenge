package com.basf.challenge.dtos;

import com.basf.challenge.entities.NamedEntities;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PatentDTO {

    private String id;
    private String year;
    private String title;
    private String abstrct;
    private List<NamedEntities> nerOutput;

}
